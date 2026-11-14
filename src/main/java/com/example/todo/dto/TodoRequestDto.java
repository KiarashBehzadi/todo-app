package com.example.todo.dto;

import com.example.todo.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record TodoRequestDto(
        @NotBlank
        @Size(min = 3, max = 100)
        String title,

        @Size(max = 500)
        String description,

        @NotBlank
        Priority priority
) { }
