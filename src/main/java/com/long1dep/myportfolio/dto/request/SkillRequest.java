package com.long1dep.myportfolio.dto.request;
 
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 100, message = "Level must be at most 100")
    private int level;
}
