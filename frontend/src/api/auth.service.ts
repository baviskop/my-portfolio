import api from './axios';
import { LoginResponse } from '../types';

export const authService = {
  login: async (credentials: any) => {
    const response = await api.post<LoginResponse>('/auth/login', credentials);
    return response.data;
  },
  logout: () => {
    localStorage.removeItem('token');
  }
};
