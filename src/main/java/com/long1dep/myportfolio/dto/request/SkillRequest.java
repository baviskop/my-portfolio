package com.long1dep.myportfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
    private String name;
    private String category;
    private int level;
}
