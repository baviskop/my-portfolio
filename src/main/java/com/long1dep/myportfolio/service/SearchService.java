package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.response.BlogResponse;
import com.long1dep.myportfolio.dto.response.ProjectResponse;
import com.long1dep.myportfolio.dto.response.SearchResponse;
import com.long1dep.myportfolio.entity.Blog;
import com.long1dep.myportfolio.entity.Project;
import com.long1dep.myportfolio.repository.BlogRepository;
import com.long1dep.myportfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final BlogRepository blogRepo;
    private final ProjectRepository projectRepo;

    public SearchResponse search(String q) {
        List<BlogResponse> blogs = blogRepo.findTop5ByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(q, q)
                .stream()
                .map(this::toBlogDto)
                .toList();

        List<ProjectResponse> projects = projectRepo.findTop5ByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q)
                .stream()
                .map(this::toProjectDto)
                .toList();

        return new SearchResponse(blogs, projects);
    }

    private BlogResponse toBlogDto(Blog b) {
        return new BlogResponse(
                b.getId(),
                b.getSlug(),
                b.getTitle(),
                b.getSummary(),
                b.getContent(),
                b.getTags(),
                b.getCreatedAt()
        );
    }

    private ProjectResponse toProjectDto(Project p) {
        return new ProjectResponse(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getTechnologies(),
                p.getGithubUrl(),
                p.getDemoUrl(),
                p.getImageUrl()
        );
    }
}
