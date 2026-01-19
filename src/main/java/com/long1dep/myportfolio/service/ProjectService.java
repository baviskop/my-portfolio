package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.ProjectRequest;
import com.long1dep.myportfolio.dto.response.ProjectResponse;
import com.long1dep.myportfolio.entity.Project;
import com.long1dep.myportfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepo;

    //PUBLIC
    public List<ProjectResponse> getAll() {
        return projectRepo.findAll()
                .stream()
                .map(this:: toResponse)
                .toList();
    }

    public ProjectResponse getById(Long id) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return toResponse(p);
    }

    //ADMIN
    public ProjectResponse create(ProjectRequest request) {
        Project p = new Project();
        mapRequest(p, request);
        return toResponse(projectRepo.save(p));
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        mapRequest(p, request);
        return toResponse(projectRepo.save(p));
    }

    public void delete(Long id) {
        if(!projectRepo.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepo.deleteById(id);
    }

    public void updateImage(Long id, String imageUrl) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
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
