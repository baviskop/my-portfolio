import api from './axios';
import { ProjectResponse, ProjectRequest } from '../types';

export const projectService = {
  getAll: async () => {
    const response = await api.get<ProjectResponse[]>('/projects');
    return response.data;
  },
  getById: async (id: number) => {
    const response = await api.get<ProjectResponse>(`/projects/${id}`);
    return response.data;
  },
  create: async (data: ProjectRequest) => {
    const response = await api.post<ProjectResponse>('/admin/projects', data);
    return response.data;
  },
  update: async (id: number, data: ProjectRequest) => {
    const response = await api.put<ProjectResponse>(`/admin/projects/${id}`, data);
    return response.data;
  },
  delete: async (id: number) => {
    await api.delete(`/admin/projects/${id}`);
  }
};
