package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.enums.Priority;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }


    @Override
    public TodoResponseDto createTodo(Long userId, TodoRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = new Todo();
        todo.setTitle( dto.getTitle() );
        todo.setDescription( dto.getDescription() );
        todo.setPriority( Priority.valueOf(dto.getPriority()) );
        todo.setCompleted(false);
        todo.setUser(user);

        todoRepository.save(todo);

        return toDto(todo);
    }



    @Override
    public List<TodoResponseDto> getUserTodos(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found : " + userId));

        List<Todo> todos = todoRepository.findByUserId(userId);

        return todos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // mapping Todo-entity to Todo-Dto
    private TodoResponseDto toDto(Todo todo){
        return new TodoResponseDto(
                todo.getTodoId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getPriority(),
                todo.isCompleted()
        );
    }
}
