package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable long userId) {
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
            @RequestBody UserUpdateRequestDto userUpdateRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        UserResponseDto userResponseDto = userService.updateUser(String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)), userUpdateRequestDto);
        return new ResponseEntity<>(userResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUser(
            HttpServletRequest httpServletRequest
    ) {
        userService.deleteUser(String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)));
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
