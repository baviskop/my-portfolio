package com.long1dep.myportfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
    private String title;
    private String summary;
    private String content;
    private String tags;
}
