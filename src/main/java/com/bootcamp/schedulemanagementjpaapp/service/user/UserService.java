package com.bootcamp.schedulemanagementjpaapp.service.user;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserLoginRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserLoginResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.User;

import java.util.List;

public interface UserService {
    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
    UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);
    UserResponseDto getUser(Long userId);
    List<UserResponseDto> getUserList();
    UserResponseDto updateUser(String email, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(String email);
}
