package com.long1dep.myportfolio.controller.admin;

import com.long1dep.myportfolio.dto.request.ProjectRequest;
import com.long1dep.myportfolio.dto.response.ProjectResponse;
import com.long1dep.myportfolio.service.FileStorageService;
import com.long1dep.myportfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/projects")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminProjectController {
    private final ProjectService projectService;
    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable Long id, @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //UPLOAD IMAGE
    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file")MultipartFile file) throws IOException {
        String url = fileStorageService.saveImage(file);
        projectService.updateImage(id, url);

        return ResponseEntity.ok().body(
                Map.of("imageUrl", url)
        );
    }

}
