package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UsersResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto registerUserRequestDto) {
        UserResponseDto userResponseDto = userService.registerUser(registerUserRequestDto);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        UsersResponseDto usersResponseDto = userService.getAllUsers();
        return new ResponseEntity<>(usersResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequestDto updateUserRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userId, updateUserRequestDto);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
