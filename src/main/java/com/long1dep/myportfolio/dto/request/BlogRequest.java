package com.long1dep.myportfolio.dto.request;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be less than 200 characters")
    private String title;

    @NotBlank(message = "Summary is required")
    @Size(max = 500, message = "Summary must be less than 500 characters")
    private String summary;

    @NotBlank(message = "Content is required")
    private String content;

    private String tags;
}
