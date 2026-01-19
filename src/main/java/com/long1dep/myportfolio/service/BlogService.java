package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.BlogRequest;
import com.long1dep.myportfolio.dto.response.BlogResponse;
import com.long1dep.myportfolio.entity.Blog;
import com.long1dep.myportfolio.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepo;

    //PUBLIC
    public Page<BlogResponse> getAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Blog> blogs;

        if(search == null || search.isBlank()) {
            blogs = blogRepo.findAll(pageable);
        }else {
            blogs = blogRepo.findByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(search, search, pageable);
        }

        return blogs.map(this::toResponse);
    }

    public BlogResponse getBySlug(String slug) {
        Blog blog = blogRepo.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        return toResponse(blog);
    }

    public BlogResponse getById(Long id) {
        Blog blog = blogRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        blog.setViews(blog.getViews() + 1);

        return toResponse(blog);
    }


    //ADMIN
    public BlogResponse create(BlogRequest request) {
        Blog blog = new Blog();
        mapRequest(blog, request);
        blog.setSlug(toSlug(request.getTitle()));
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        return toResponse(blogRepo.save(blog));
    }

    public BlogResponse upadte(Long id, BlogRequest request) {
        Blog blog = blogRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        mapRequest(blog, request);
        blog.setSlug(toSlug(request.getTitle()));
        blog.setUpdatedAt(LocalDateTime.now());

        return toResponse(blogRepo.save(blog));
    }
    public void delete(Long id) {
        if(!blogRepo.existsById(id)) {
            throw new RuntimeException("Blog not found");
        }
        blogRepo.deleteById(id);
    }

    //Helper
    private BlogResponse toResponse(Blog b) {
        return new BlogResponse(
                b.getId(),
                b.getSlug(),
                b.getTitle(),
                b.getSummary(),
                b.getContent(),
                b.getTags(),
                b.getViews(),
                b.getCreatedAt()
        );
    }

    private void mapRequest(Blog blog, BlogRequest r) {
        blog.setTitle(r.getTitle());
        blog.setSummary(r.getSummary());
        blog.setContent(r.getContent());
        blog.setTags(r.getTags());
    }

    //title -> seo-friendly slug
    private String toSlug(String input) {
        String nowhitespace = input.trim().replaceAll("\\s+", "-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        return normalized
                .replaceAll("[^\\w-]", "")
                .toLowerCase(Locale.ENGLISH);
    }
}