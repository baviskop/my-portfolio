package com.long1dep.myportfolio.controller.admin;

import com.long1dep.myportfolio.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UploadController {
    private final FileStorageService fileStorageService;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file")MultipartFile file
            ) throws IOException {

        String url = fileStorageService.saveImage(file);
        return ResponseEntity.ok(Map.of("url", url));
    }

    @PostMapping("/cv")
    public ResponseEntity<?> uploadCV(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = fileStorageService.saveCV(file);
        return ResponseEntity.ok(Map.of("url", url));
    }
}
