package com.long1dep.myportfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResponse {
    private List<BlogResponse> blogs;
    private List<ProjectResponse> projects;

}
