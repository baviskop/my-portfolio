package com.long1dep.myportfolio.controller.publicapi;

import com.long1dep.myportfolio.dto.response.ProjectResponse;
import com.long1dep.myportfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponse> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public ProjectResponse getById(@PathVariable Long id) {
        return projectService.getById(id);
    }
}
