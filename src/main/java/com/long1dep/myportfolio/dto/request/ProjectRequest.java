package com.long1dep.myportfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private String title;
    private String description;
    private String technologies;
    private String githubUrl;
    private String demoUrl;
//    private String image_url;
}
