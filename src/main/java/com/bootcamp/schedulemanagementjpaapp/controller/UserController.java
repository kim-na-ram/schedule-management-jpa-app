package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto userRegisterResponseDto = userService.registerUser(userRegisterRequestDto);
        return new ResponseEntity<>(userRegisterResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserList() {
        List<UserResponseDto> userListResponseDto = userService.getUserList();
        return new ResponseEntity<>(userListResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userId, userUpdateRequestDto);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
