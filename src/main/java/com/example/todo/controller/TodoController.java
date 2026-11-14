package com.example.todo.controller;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@PathVariable Long userId,
                                      @Valid @RequestBody TodoRequestDto dto) {

        TodoResponseDto response = todoService.createTodo(userId, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping
    public List<TodoResponseDto> getTodos(@PathVariable Long userId){
        return todoService.getUserTodos(userId);
    }


}
