import React, { useEffect, useState } from 'react';
import AdminLayout from '../../components/AdminLayout';
import { projectService } from '../../api';
import { ProjectResponse, ProjectRequest } from '../../types';
import { Plus, Edit2, Trash2, X, Link as LinkIcon, Github, Image as ImageIcon } from 'lucide-react';
import { cn } from '../../utils/cn';

const ProjectManagement = () => {
  const [projects, setProjects] = useState<ProjectResponse[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingProject, setEditingProject] = useState<ProjectResponse | null>(null);
  const [formData, setFormData] = useState<ProjectRequest>({
    title: '',
    description: '',
    techStack: [],
    projectUrl: '',
    githubUrl: '',
    imageUrl: '',
  });

  const fetchProjects = async () => {
    try {
      const data = await projectService.getAll();
      setProjects(data);
    } catch (err) {
      console.error('Failed to fetch projects', err);
    }
  };

  useEffect(() => {
    fetchProjects();
  }, []);

  const handleOpenModal = (project: ProjectResponse | null = null) => {
    if (project) {
      setEditingProject(project);
      setFormData({
        title: project.title,
        description: project.description,
        techStack: project.techStack,
        projectUrl: project.projectUrl || '',
        githubUrl: project.githubUrl || '',
        imageUrl: project.imageUrl || '',
      });
    } else {
      setEditingProject(null);
      setFormData({
        title: '',
        description: '',
        techStack: [],
        projectUrl: '',
        githubUrl: '',
        imageUrl: '',
      });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingProject(null);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (editingProject) {
        await projectService.update(editingProject.id, formData);
      } else {
        await projectService.create(formData);
      }
      fetchProjects();
      handleCloseModal();
    } catch (err) {
      console.error('Failed to save project', err);
    }
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this project?')) {
      try {
        await projectService.delete(id);
        fetchProjects();
      } catch (err) {
        console.error('Failed to delete project', err);
      }
    }
  };

  return (
    <AdminLayout>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold mb-2">Projects</h1>
          <p className="text-slate-400">Manage your portfolio showcase.</p>
        </div>
        <button
          onClick={() => handleOpenModal()}
          className="bg-primary hover:bg-blue-600 px-6 py-2 rounded-xl font-bold flex items-center gap-2 transition-all"
        >
          <Plus size={20} /> Add Project
        </button>
      </div>

      <div className="grid gap-4">
        {projects.map((project) => (
          <div key={project.id} className="bg-white/5 p-6 rounded-2xl border border-white/10 flex items-center gap-6">
            <div className="w-24 h-24 rounded-xl overflow-hidden bg-slate-800 shrink-0">
              <img src={project.imageUrl || ''} alt="" className="w-full h-full object-cover" />
            </div>
            <div className="flex-grow">
              <h3 className="text-xl font-bold mb-1">{project.title}</h3>
              <p className="text-slate-400 text-sm line-clamp-1 mb-2">{project.description}</p>
              <div className="flex gap-2">
                {project.techStack.map(t => (
                  <span key={t} className="px-2 py-0.5 bg-white/5 text-slate-400 text-xs rounded">{t}</span>
                ))}
              </div>
            </div>
            <div className="flex items-center gap-2">
              <button
                onClick={() => handleOpenModal(project)}
                className="p-2 text-slate-400 hover:text-primary hover:bg-primary/10 rounded-lg transition-all"
              >
                <Edit2 size={20} />
              </button>
              <button
                onClick={() => handleDelete(project.id)}
                className="p-2 text-slate-400 hover:text-red-400 hover:bg-red-400/10 rounded-lg transition-all"
              >
                <Trash2 size={20} />
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Modal */}
      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/60 backdrop-blur-sm">
          <div className="bg-slate-900 border border-white/10 w-full max-w-2xl rounded-3xl shadow-2xl overflow-hidden">
            <div className="p-6 border-b border-white/5 flex items-center justify-between">
              <h2 className="text-2xl font-bold">{editingProject ? 'Edit Project' : 'Add New Project'}</h2>
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
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-primary outline-none"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Description</label>
                <textarea
                  required
                  rows={3}
                  value={formData.description}
                  onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-primary outline-none"
                />
              </div>
              <div className="grid md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-slate-400 mb-2 flex items-center gap-2">
                    <LinkIcon size={16} /> Project URL
                  </label>
                  <input
                    type="url"
                    value={formData.projectUrl}
                    onChange={(e) => setFormData({ ...formData, projectUrl: e.target.value })}
                    className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-primary outline-none"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-slate-400 mb-2 flex items-center gap-2">
                    <Github size={16} /> GitHub URL
                  </label>
                  <input
                    type="url"
                    value={formData.githubUrl}
                    onChange={(e) => setFormData({ ...formData, githubUrl: e.target.value })}
                    className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-primary outline-none"
                  />
                </div>
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2 flex items-center gap-2">
                  <ImageIcon size={16} /> Image URL
                </label>
                <input
                  type="text"
                  value={formData.imageUrl}
                  onChange={(e) => setFormData({ ...formData, imageUrl: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-primary outline-none"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Tech Stack (comma separated)</label>
                <input
                  type="text"
                  value={formData.techStack.join(', ')}
                  onChange={(e) => setFormData({ ...formData, techStack: e.target.value.split(',').map(s => s.trim()).filter(s => s !== '') })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-primary outline-none"
                  placeholder="React, Spring Boot, Tailwind"
                />
              </div>
            </form>
            <div className="p-6 border-t border-white/5 flex justify-end gap-3">
              <button onClick={handleCloseModal} className="px-6 py-2 text-slate-400 hover:text-white font-medium">Cancel</button>
              <button onClick={handleSubmit} className="bg-primary hover:bg-blue-600 px-8 py-2 rounded-xl font-bold transition-all">
                {editingProject ? 'Update Project' : 'Create Project'}
              </button>
            </div>
          </div>
        </div>
      )}
    </AdminLayout>
  );
};

export default ProjectManagement;
