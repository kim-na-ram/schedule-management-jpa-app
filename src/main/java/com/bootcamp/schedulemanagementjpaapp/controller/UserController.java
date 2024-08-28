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

    @GetMapping("/users/{userEmail}")
    public ResponseEntity<?> getUser(@PathVariable("userEmail") String userEmail) {
        UserResponseDto userResponseDto = userService.getUser(userEmail);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserList() {
        List<UserResponseDto> userListResponseDto = userService.getUserList();
        return new ResponseEntity<>(userListResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/users/{userEmail}")
    public ResponseEntity<?> updateUser(
            @PathVariable("userEmail") String userEmail,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        UserResponseDto userResponseDto = userService.updateUser(userEmail, userUpdateRequestDto);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/users/{userEmail}")
    public ResponseEntity<?> deleteUser(@PathVariable("userEmail") String userEmail) {
        userService.deleteUser(userEmail);
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
