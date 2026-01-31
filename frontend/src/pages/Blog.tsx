import React, { useEffect, useState } from 'react';
import { motion } from 'framer-motion';
import { Calendar, User } from 'lucide-react';
import Layout from '../components/Layout';
import { blogService } from '../api';
import { BlogResponse } from '../types';

const Blog = () => {
  const [blogs, setBlogs] = useState<BlogResponse[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    blogService.getAll()
      .then(setBlogs)
      .catch(console.error)
      .finally(() => setLoading(false));
  }, []);

  return (
    <Layout>
      <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="mb-12">
          <h1 className="text-4xl font-bold mb-4">Blog</h1>
          <p className="text-slate-400">Thoughts, tutorials, and insights about software development.</p>
        </div>

        {loading ? (
          <div className="space-y-8">
            {[1, 2].map((i) => (
              <div key={i} className="h-64 bg-white/5 rounded-2xl animate-pulse" />
            ))}
          </div>
        ) : (
          <div className="space-y-12">
            {blogs.map((blog, i) => (
              <motion.article
                key={blog.id}
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ delay: i * 0.1 }}
                className="group flex flex-col md:flex-row gap-8 items-start"
              >
                <div className="w-full md:w-1/3 aspect-[16/9] md:aspect-square overflow-hidden rounded-2xl border border-white/10">
                  <img 
                    src={blog.thumbnailUrl || 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=800&q=80'} 
                    alt={blog.title}
                    className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
                  />
                </div>
                <div className="flex-1">
                  <div className="flex items-center gap-4 text-xs text-slate-500 mb-3">
                    <span className="flex items-center gap-1">
                      <Calendar size={14} />
                      {new Date(blog.createdAt).toLocaleDateString()}
                    </span>
                    <span className="flex items-center gap-1">
                      <User size={14} />
                      {blog.authorName}
                    </span>
                  </div>
                  <h2 className="text-2xl font-bold mb-3 group-hover:text-primary transition-colors">
                    {blog.title}
                  </h2>
                  <p className="text-slate-400 mb-6 line-clamp-3 leading-relaxed">
                    {blog.summary}
                  </p>
                  <button className="text-primary font-semibold flex items-center gap-2 hover:gap-3 transition-all">
                    Read More <span aria-hidden="true">→</span>
                  </button>
                </div>
              </motion.article>
            ))}
          </div>
        )}
      </div>
    </Layout>
  );
};

export default Blog;
