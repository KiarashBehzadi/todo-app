package com.example.todo.dto;


public record UserResponseDto(
        Long userId,
        String username
) { }


    private Long userId;
    private String username;
}
