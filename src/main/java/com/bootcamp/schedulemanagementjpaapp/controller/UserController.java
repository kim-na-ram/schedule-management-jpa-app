package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserList() {
        List<UserResponseDto> userListResponseDto = userService.getUserList();
        return new ResponseEntity<>(userListResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/users")
    public ResponseEntity<?> updateUser(
            @RequestParam String email,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        UserResponseDto userResponseDto = userService.updateUser(email, userUpdateRequestDto);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
