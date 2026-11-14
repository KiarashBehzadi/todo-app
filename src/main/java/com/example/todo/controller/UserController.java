package com.example.todo.controller;

import com.example.todo.dto.UserRequestDto;
import com.example.todo.dto.UserResponseDto;
import com.example.todo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid  @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto response = userService.createUser(userRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
