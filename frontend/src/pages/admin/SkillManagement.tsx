import React, { useEffect, useState } from 'react';
import AdminLayout from '../../components/AdminLayout';
import { skillService } from '../../api';
import { SkillResponse, SkillRequest } from '../../types';
import { Plus, Edit2, Trash2, X } from 'lucide-react';

const SkillManagement = () => {
  const [skills, setSkills] = useState<SkillResponse[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingSkill, setEditingSkill] = useState<SkillResponse | null>(null);
  const [formData, setFormData] = useState<SkillRequest>({
    name: '',
    category: '',
    proficiency: 80,
  });

  const fetchSkills = async () => {
    try {
      const data = await skillService.getAll();
      setSkills(data);
    } catch (err) {
      console.error('Failed to fetch skills', err);
    }
  };

  useEffect(() => {
    fetchSkills();
  }, []);

  const handleOpenModal = (skill: SkillResponse | null = null) => {
    if (skill) {
      setEditingSkill(skill);
      setFormData({
        name: skill.name,
        category: skill.category,
        proficiency: skill.proficiency,
      });
    } else {
      setEditingSkill(null);
      setFormData({
        name: '',
        category: '',
        proficiency: 80,
      });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingSkill(null);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (editingSkill) {
        await skillService.update(editingSkill.id, formData);
      } else {
        await skillService.create(formData);
      }
      fetchSkills();
      handleCloseModal();
    } catch (err) {
      console.error('Failed to save skill', err);
    }
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this skill?')) {
      try {
        await skillService.delete(id);
        fetchSkills();
      } catch (err) {
        console.error('Failed to delete skill', err);
      }
    }
  };

  return (
    <AdminLayout>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold mb-2">Skills</h1>
          <p className="text-slate-400">Manage your technical expertise.</p>
        </div>
        <button
          onClick={() => handleOpenModal()}
          className="bg-purple-500 hover:bg-purple-600 px-6 py-2 rounded-xl font-bold flex items-center gap-2 transition-all text-white"
        >
          <Plus size={20} /> Add Skill
        </button>
      </div>

      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        {skills.map((skill) => (
          <div key={skill.id} className="bg-white/5 p-6 rounded-2xl border border-white/10 flex items-center justify-between">
            <div>
              <h3 className="text-xl font-bold">{skill.name}</h3>
              <p className="text-slate-500 text-sm mb-2">{skill.category}</p>
              <div className="w-32 h-2 bg-white/10 rounded-full overflow-hidden">
                <div 
                  className="h-full bg-purple-500" 
                  style={{ width: `${skill.proficiency}%` }}
                />
              </div>
            </div>
            <div className="flex items-center gap-2">
              <button
                onClick={() => handleOpenModal(skill)}
                className="p-2 text-slate-400 hover:text-purple-400 hover:bg-purple-400/10 rounded-lg transition-all"
              >
                <Edit2 size={18} />
              </button>
              <button
                onClick={() => handleDelete(skill.id)}
                className="p-2 text-slate-400 hover:text-red-400 hover:bg-red-400/10 rounded-lg transition-all"
              >
                <Trash2 size={18} />
              </button>
            </div>
          </div>
        ))}
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/60 backdrop-blur-sm">
          <div className="bg-slate-900 border border-white/10 w-full max-w-md rounded-3xl shadow-2xl overflow-hidden">
            <div className="p-6 border-b border-white/5 flex items-center justify-between">
              <h2 className="text-2xl font-bold">{editingSkill ? 'Edit Skill' : 'New Skill'}</h2>
              <button onClick={handleCloseModal} className="text-slate-400 hover:text-white"><X size={24} /></button>
            </div>
            <form onSubmit={handleSubmit} className="p-6 space-y-4">
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Name</label>
                <input
                  type="text"
                  required
                  value={formData.name}
                  onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-purple-500 outline-none"
                  placeholder="e.g. React"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2">Category</label>
                <input
                  type="text"
                  required
                  value={formData.category}
                  onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                  className="w-full bg-white/5 border border-white/10 rounded-xl px-4 py-2.5 focus:border-purple-500 outline-none"
                  placeholder="e.g. Frontend"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-slate-400 mb-2 flex justify-between">
                  Proficiency <span>{formData.proficiency}%</span>
                </label>
                <input
                  type="range"
                  min="0"
                  max="100"
                  value={formData.proficiency}
                  onChange={(e) => setFormData({ ...formData, proficiency: parseInt(e.target.value) })}
                  className="w-full h-2 bg-white/10 rounded-lg appearance-none cursor-pointer accent-purple-500"
                />
              </div>
            </form>
            <div className="p-6 border-t border-white/5 flex justify-end gap-3">
              <button onClick={handleCloseModal} className="px-6 py-2 text-slate-400 hover:text-white font-medium">Cancel</button>
              <button onClick={handleSubmit} className="bg-purple-500 hover:bg-purple-600 px-8 py-2 rounded-xl font-bold transition-all text-white">
                {editingSkill ? 'Update Skill' : 'Add Skill'}
              </button>
            </div>
          </div>
        </div>
      )}
    </AdminLayout>
  );
};

export default SkillManagement;
