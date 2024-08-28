package com.bootcamp.schedulemanagementjpaapp.service.user;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserLoginRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserLoginResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
    UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);
    UserResponseDto getUser(String email);
    List<UserResponseDto> getUserList();
    UserResponseDto updateUser(String email, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(String email);
}
