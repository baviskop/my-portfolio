import api from './axios';
import { SkillResponse, SkillRequest } from '../types';

export const skillService = {
  getAll: async () => {
    const response = await api.get<SkillResponse[]>('/skills');
    return response.data;
  },
  create: async (data: SkillRequest) => {
    const response = await api.post<SkillResponse>('/admin/skills', data);
    return response.data;
  },
  update: async (id: number, data: SkillRequest) => {
    const response = await api.put<SkillResponse>(`/admin/skills/${id}`, data);
    return response.data;
  },
  delete: async (id: number) => {
    await api.delete(`/admin/skills/${id}`);
  }
};
