package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserLoginRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserLoginResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.SUCCESS;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto userRegisterResponseDto = userService.registerUser(userRegisterRequestDto);
        return new ResponseEntity<>(userRegisterResponseDto, SUCCESS.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        UserLoginResponseDto userLoginResponseDto = userService.loginUser(userLoginRequestDto);
        return new ResponseEntity<>(userLoginResponseDto, SUCCESS.getHttpStatus());
    }
}
