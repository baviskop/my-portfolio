package com.long1dep.myportfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

//TODO: check content-type of file (virus .exe file)
@Service
public class FileStorageService {

    private final Path root = Paths.get("uploads");

    public String saveImage(MultipartFile file) throws IOException {
        return save(file, "images", List.of("image/png", "image/jpg", "image/webp", "image/jpeg"));
    }

    public String saveCV(MultipartFile file) throws IOException {
        return save(file, "cv", List.of("application/pdf"));
    }

    public String save(MultipartFile file, String folder, List<String> types) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("Empty file");
        }

        if (!types.contains(file.getContentType())) {
            throw new RuntimeException("Invalid file type: " + file.getContentType());
        }

        String ext = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));

        String filename = UUID.randomUUID() + ext;

        Path dir = root.resolve(folder);
        Files.createDirectories(dir);

        Path target = dir.resolve(filename);
        Files.copy(file.getInputStream(), target);

        return "/files/" + folder + "/" + filename;
    }

}
