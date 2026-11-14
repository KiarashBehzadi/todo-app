package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
        @Size(min = 3, message = "Username must have at least 3 characters")
        String username,

        @NotBlank
        @Size(min = 4, max = 12, message = "Password must be between 4 and 8 characters")
        String password
) { }
