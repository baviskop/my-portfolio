export interface ProjectResponse {
  id: number;
  title: string;
  description: string;
  imageUrl: string;
  projectUrl: string;
  githubUrl: string;
  techStack: string[];
}

export interface BlogResponse {
  id: number;
  title: string;
  content: string;
  summary: string;
  thumbnailUrl: string;
  createdAt: string;
  updatedAt: string;
  authorName: string;
}

export interface SkillResponse {
  id: number;
  name: string;
  category: string;
  proficiency: number;
}

export interface LoginResponse {
  token: string;
  type: string;
  username: string;
  roles: string[];
}

export interface User {
  username: string;
  roles: string[];
}

export interface ProjectRequest {
  title: string;
  description: string;
  imageUrl?: string;
  projectUrl?: string;
  githubUrl?: string;
  techStack: string[];
}

export interface BlogRequest {
  title: string;
  content: string;
  summary: string;
  thumbnailUrl?: string;
}

export interface SkillRequest {
  name: string;
  category: string;
  proficiency: number;
}

export interface ContactRequest {
  name: string;
  email: string;
  subject: string;
  message: string;
}
