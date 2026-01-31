package com.long1dep.myportfolio.controller.admin;

import com.long1dep.myportfolio.dto.request.SkillRequest;
import com.long1dep.myportfolio.dto.response.SkillResponse;
import com.long1dep.myportfolio.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/skills")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminSkillController {
    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillResponse> create(@Valid @RequestBody SkillRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(skillService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse> update(@PathVariable Long id, @Valid @RequestBody SkillRequest request) {
        return ResponseEntity
                .ok(skillService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
