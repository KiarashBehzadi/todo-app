package com.example.todo.dto;


import com.example.todo.enums.Priority;

public record TodoResponseDto(
        Long todoId,
        String title,
        String description,
        Priority priority,
        boolean completed

) { }
