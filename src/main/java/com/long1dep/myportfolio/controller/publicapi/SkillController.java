package com.long1dep.myportfolio.controller.publicapi;

import com.long1dep.myportfolio.dto.response.SkillResponse;
import com.long1dep.myportfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public List<SkillResponse> getAll() {
        return skillService.getAll();
    }
}
