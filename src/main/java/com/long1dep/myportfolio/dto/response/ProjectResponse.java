package com.long1dep.myportfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String technologies;
    private String githubUrl;
    private String demoUrl;
    private String imageUrl;
    private long views;
}
