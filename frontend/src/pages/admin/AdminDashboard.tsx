import React from 'react';
import AdminLayout from '../../components/AdminLayout';
import { Briefcase, FileText, Settings, MessageSquare } from 'lucide-react';

const AdminDashboard = () => {
  const stats = [
    { name: 'Total Projects', value: '12', icon: <Briefcase size={24} className="text-blue-400" /> },
    { name: 'Blog Posts', value: '8', icon: <FileText size={24} className="text-accent" /> },
    { name: 'Total Skills', value: '15', icon: <Settings size={24} className="text-purple-400" /> },
    { name: 'New Messages', value: '3', icon: <MessageSquare size={24} className="text-green-400" /> },
  ];

  return (
    <AdminLayout>
      <div className="mb-8">
        <h1 className="text-3xl font-bold mb-2">Welcome Back!</h1>
        <p className="text-slate-400">Here's what's happening with your portfolio.</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        {stats.map((stat) => (
          <div key={stat.name} className="bg-white/5 p-6 rounded-2xl border border-white/10">
            <div className="flex items-center justify-between mb-4">
              <div className="p-3 bg-white/5 rounded-xl">{stat.icon}</div>
              <span className="text-2xl font-bold">{stat.value}</span>
            </div>
            <p className="text-slate-400 text-sm font-medium">{stat.name}</p>
          </div>
        ))}
      </div>

      <div className="grid lg:grid-cols-2 gap-8">
        <div className="bg-white/5 p-8 rounded-2xl border border-white/10">
          <h2 className="text-xl font-bold mb-6">Recent Activity</h2>
          <div className="space-y-4">
            <p className="text-slate-500 italic">No recent activity to show.</p>
          </div>
        </div>
        <div className="bg-white/5 p-8 rounded-2xl border border-white/10">
          <h2 className="text-xl font-bold mb-6">Quick Actions</h2>
          <div className="grid grid-cols-2 gap-4">
            <button className="p-4 bg-primary/10 hover:bg-primary/20 text-primary rounded-xl transition-colors font-medium">
              Add New Project
            </button>
            <button className="p-4 bg-accent/10 hover:bg-accent/20 text-accent rounded-xl transition-colors font-medium">
              Write Blog Post
            </button>
          </div>
        </div>
      </div>
    </AdminLayout>
  );
};

export default AdminDashboard;
