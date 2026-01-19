package com.long1dep.myportfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private Long id;
    private String slug;
    private String title;
    private String summary;
    private String content;
    private String tags;
    private LocalDateTime createdAt;
}
