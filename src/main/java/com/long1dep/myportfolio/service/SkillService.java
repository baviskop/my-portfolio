package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.SkillRequest;
import com.long1dep.myportfolio.dto.response.SkillResponse;
import com.long1dep.myportfolio.entity.Skill;
import com.long1dep.myportfolio.exception.ResourceNotFoundException;
import com.long1dep.myportfolio.repository.SkillRepository;
import com.long1dep.myportfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepo;
    private final UserRepository userRepo;

    //PUBLIC
    public List<SkillResponse> getAll() {
        return skillRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //ADMIN
    public SkillResponse create(SkillRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        com.long1dep.myportfolio.entity.User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setLevel(request.getLevel());
        skill.setUser(user);

        return toResponse(skillRepo.save(skill));
    }

    public SkillResponse update(Long id, SkillRequest request) {
        Skill skill = skillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setLevel(request.getLevel());

        return toResponse(skillRepo.save(skill));
    }

    public void delete(Long id) {
        if(!skillRepo.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
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
