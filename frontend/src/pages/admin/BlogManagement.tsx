import React, { useEffect, useState } from 'react';
import AdminLayout from '../../components/AdminLayout';
import { blogService } from '../../api';
import { BlogResponse, BlogRequest } from '../../types';
import { Plus, Edit2, Trash2, X } from 'lucide-react';

const BlogManagement = () => {
  const [blogs, setBlogs] = useState<BlogResponse[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingBlog, setEditingBlog] = useState<BlogResponse | null>(null);
  const [formData, setFormData] = useState<BlogRequest>({
    title: '',
    summary: '',
    content: '',
    thumbnailUrl: '',
  });

  const fetchBlogs = async () => {
    try {
      const data = await blogService.getAll();
      setBlogs(data);
    } catch (err) {
      console.error('Failed to fetch blogs', err);
    }
  };

  useEffect(() => {
    fetchBlogs();
  }, []);

  const handleOpenModal = (blog: BlogResponse | null = null) => {
    if (blog) {
      setEditingBlog(blog);
      setFormData({
        title: blog.title,
        summary: blog.summary,
        content: blog.content,
        thumbnailUrl: blog.thumbnailUrl || '',
      });
    } else {
      setEditingBlog(null);
      setFormData({
        title: '',
        summary: '',
        content: '',
        thumbnailUrl: '',
      });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingBlog(null);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (editingBlog) {
        await blogService.update(editingBlog.id, formData);
      } else {
        await blogService.create(formData);
      }
      fetchBlogs();
      handleCloseModal();
    } catch (err) {
      console.error('Failed to save blog', err);
    }
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this blog?')) {
      try {
        await blogService.delete(id);
        fetchBlogs();
      } catch (err) {
        console.error('Failed to delete blog', err);
      }
    }
  };

  return (
    <AdminLayout>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold mb-2">Blogs</h1>
          <p className="text-slate-400">Write and manage your articles.</p>
        </div>
        <button
          onClick={() => handleOpenModal()}
          className="bg-accent hover:bg-orange-500 px-6 py-2 rounded-xl font-bold flex items-center gap-2 transition-all text-slate-900"
        >
          <Plus size={20} /> New Post
        </button>
      </div>

      <div className="space-y-4">
        {blogs.map((blog) => (
          <div key={blog.id} className="bg-white/5 p-6 rounded-2xl border border-white/10 flex items-center gap-6">
            <div className="flex-grow">
              <h3 className="text-xl font-bold mb-1">{blog.title}</h3>
              <p className="text-slate-500 text-sm mb-2">Created: {new Date(blog.createdAt).toLocaleDateString()}</p>
              <p className="text-slate-400 text-sm line-clamp-1">{blog.summary}</p>
            </div>
            <div className="flex items-center gap-2">
              <button
                onClick={() => handleOpenModal(blog)}
                className="p-2 text-slate-400 hover:text-accent hover:bg-accent/10 rounded-lg transition-all"
              >
                <Edit2 size={20} />
              </button>
              <button
                onClick={() => handleDelete(blog.id)}
                className="p-2 text-slate-400 hover:text-red-400 hover:bg-red-400/10 rounded-lg transition-all"
              >
                <Trash2 size={20} />
              </button>
            </div>
          </div>
        ))}
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/60 backdrop-blur-sm">
          <div className="bg-slate-900 border border-white/10 w-full max-w-3xl rounded-3xl shadow-2xl overflow-hidden">
            <div className="p-6 border-b border-white/5 flex items-center justify-between">
              <h2 className="text-2xl font-bold">{editingBlog ? 'Edit Blog' : 'New Blog Post'}</h2>
              <button onClick={handleCloseModal} className="text-slate-400 hover:text-white"><X size={24} /></button>
            </div>
            <form onSubmit={handleSubmit} className="p-6 space-y-4 max-h-[70vh] overflow-y-auto">
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Title</label>
                <input
                  type="text"
                  required
                  value={formData.title}
                  onChange={(e) => setFormData({ ...formData, title: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-accent outline-none"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Summary</label>
                <input
                  type="text"
                  required
                  value={formData.summary}
                  onChange={(e) => setFormData({ ...formData, summary: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-accent outline-none"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Content (Markdown supported)</label>
                <textarea
                  required
                  rows={8}
                  value={formData.content}
                  onChange={(e) => setFormData({ ...formData, content: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-accent outline-none font-mono text-sm"
                />
              </div>
            </form>
            <div className="p-6 border-t border-white/5 flex justify-end gap-3">
              <button onClick={handleCloseModal} className="px-6 py-2 text-slate-400 hover:text-white font-medium">Cancel</button>
              <button onClick={handleSubmit} className="bg-accent hover:bg-orange-500 px-8 py-2 rounded-xl font-bold transition-all text-slate-900">
                {editingBlog ? 'Update Post' : 'Publish Post'}
              </button>
            </div>
          </div>
        </div>
      )}
    </AdminLayout>
  );
};

export default BlogManagement;
