package com.long1dep.myportfolio.repository;

import com.long1dep.myportfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findTop5ByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
