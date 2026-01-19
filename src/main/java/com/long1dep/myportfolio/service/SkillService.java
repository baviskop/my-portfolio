package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.SkillRequest;
import com.long1dep.myportfolio.dto.response.SkillResponse;
import com.long1dep.myportfolio.entity.Skill;
import com.long1dep.myportfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepo;
    private final SkillRepository skillRepository;

    //PUBLIC
    public List<SkillResponse> getAll() {
        return skillRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //ADMIN
    public SkillResponse create(SkillRequest request) {
        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setLevel(request.getLevel());

        return toResponse(skillRepo.save(skill));
    }

    public SkillResponse update(Long id, SkillRequest request) {
        Skill skill = skillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found!"));
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setLevel(request.getLevel());

        return toResponse(skillRepo.save(skill));
    }

    public void delete(Long id) {
        if(!skillRepo.existsById(id)) {
            throw new RuntimeException("Skill not found!");
        }
        skillRepo.deleteById(id);
    }

    //Helper
    private SkillResponse toResponse(Skill s) {
        return new SkillResponse(
                s.getId(),
                s.getName(),
                s.getCategory(),
                s.getLevel()
        );
    }
}
