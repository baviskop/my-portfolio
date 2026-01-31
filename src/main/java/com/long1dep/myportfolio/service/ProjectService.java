package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.ProjectRequest;
import com.long1dep.myportfolio.dto.response.ProjectResponse;
import com.long1dep.myportfolio.entity.Project;
import com.long1dep.myportfolio.exception.ResourceNotFoundException;
import com.long1dep.myportfolio.repository.ProjectRepository;
import com.long1dep.myportfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    //PUBLIC
    public List<ProjectResponse> getAll() {
        return projectRepo.findAll()
                .stream()
                .map(this:: toResponse)
                .toList();
    }

    public ProjectResponse getById(Long id) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return toResponse(p);
    }

    //ADMIN
    public ProjectResponse create(ProjectRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        com.long1dep.myportfolio.entity.User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project p = new Project();
        mapRequest(p, request);
        p.setUser(user);
        return toResponse(projectRepo.save(p));
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        mapRequest(p, request);
        return toResponse(projectRepo.save(p));
    }

    public void delete(Long id) {
        if(!projectRepo.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepo.deleteById(id);
    }

    public void updateImage(Long id, String imageUrl) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        p.setImageUrl(imageUrl);
        projectRepo.save(p);
    }

    //Helper
    public ProjectResponse toResponse(Project p) {
        return new ProjectResponse(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getTechnologies(),
                p.getGithubUrl(),
                p.getDemoUrl(),
                p.getImageUrl(),
                p.getViews()
        );
    }

    public void mapRequest(Project p, ProjectRequest request) {
        p.setTitle(request.getTitle());
        p.setDescription(request.getDescription());
        p.setTechnologies(request.getTechnologies());
        p.setGithubUrl(request.getGithubUrl());
        p.setDemoUrl(request.getDemoUrl());
    }
}
