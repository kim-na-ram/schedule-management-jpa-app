package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UsersResponseDto;

public interface UserService {
    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
    UsersResponseDto getAllUsers();
    UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(Long id);
}
