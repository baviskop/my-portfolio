import api from './axios';
import { BlogResponse, BlogRequest } from '../types';

export const blogService = {
  getAll: async () => {
    const response = await api.get<BlogResponse[]>('/blogs');
    return response.data;
  },
  getById: async (id: number) => {
    const response = await api.get<BlogResponse>(`/blogs/${id}`);
    return response.data;
  },
  create: async (data: BlogRequest) => {
    const response = await api.post<BlogResponse>('/admin/blogs', data);
    return response.data;
  },
  update: async (id: number, data: BlogRequest) => {
    const response = await api.put<BlogResponse>(`/admin/blogs/${id}`, data);
    return response.data;
  },
  delete: async (id: number) => {
    await api.delete(`/admin/blogs/${id}`);
  }
};
