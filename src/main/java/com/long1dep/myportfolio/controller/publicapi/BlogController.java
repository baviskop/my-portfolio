package com.long1dep.myportfolio.controller.publicapi;

import com.long1dep.myportfolio.dto.response.BlogResponse;
import com.long1dep.myportfolio.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public Page<BlogResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search) {
        return blogService.getAll(page, size, search);
    }

    @GetMapping("/{slug}")
    public BlogResponse getBySlug(@PathVariable String slug) {
        return blogService.getBySlug(slug);
    }
}
