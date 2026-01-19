package com.long1dep.myportfolio.controller.admin;

import com.long1dep.myportfolio.dto.request.BlogRequest;
import com.long1dep.myportfolio.dto.response.BlogResponse;
import com.long1dep.myportfolio.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminBlogController {
    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogResponse> create(@RequestBody BlogRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(blogService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> update(@PathVariable Long id, @RequestBody BlogRequest request) {
        return ResponseEntity.ok(blogService.upadte(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
