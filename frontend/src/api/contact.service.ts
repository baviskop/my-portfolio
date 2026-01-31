import api from './axios';
import { ContactRequest } from '../types';

export const contactService = {
  sendMessage: async (data: ContactRequest) => {
    const response = await api.post('/public/contact', data);
    return response.data;
  },
  getAllMessages: async () => {
    const response = await api.get('/admin/contacts');
    return response.data;
  }
};

export const uploadService = {
  uploadImage: async (file: File) => {
    const formData = new FormData();
    formData.append('file', file);
    const response = await api.post<{ url: string }>('/admin/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  }
};
