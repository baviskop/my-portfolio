package com.long1dep.myportfolio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    @NotBlank
    private String name;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;
}
