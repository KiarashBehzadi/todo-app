package com.example.todo.service;

import com.example.todo.dto.UserRequestDto;
import com.example.todo.dto.UserResponseDto;
import com.example.todo.entity.User;
import com.example.todo.exception.UserAlreadyExistsException;
import com.example.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByUsername(userRequestDto.username())){
            throw new UserAlreadyExistsException("User already exists: " + userRequestDto.username());
        }

        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());

        User savedUser = userRepository.save(user);

        return toDto(savedUser);
    }


    // maps User-entity to UserResponse-Dto
    private UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getUsername()
        );
    }

}
