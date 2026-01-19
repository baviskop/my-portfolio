package com.long1dep.myportfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
