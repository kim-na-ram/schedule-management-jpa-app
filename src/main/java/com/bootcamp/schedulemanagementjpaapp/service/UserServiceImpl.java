package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UsersResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJPARepository userRepository;

    @Override
    @Transactional
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        try {
            User user = userRepository.save(userRequestDto.toEntity());
            return new UserResponseDto(user);
        } catch (Exception e) {
            throw new ApiException(FAIL_REGISTER_USER);
        }
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(NOT_EXIST_USER));

         try {
             return new UserResponseDto(user);
         } catch (Exception e) {
             throw new ApiException(FAIL_GET_USER);
         }
    }

    @Override
    public UsersResponseDto getAllUsers() {
        try {
            List<UserResponseDto> userResponseDtoList = userRepository.findAll()
                    .stream()
                    .map(UserResponseDto::new)
                    .toList();

            return UsersResponseDto.builder().userList(userResponseDtoList).build();
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_USER);
        }
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto updateUserRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(NOT_EXIST_USER));

        try {
            user.updateUser(updateUserRequestDto);
            return new UserResponseDto(userRepository.save(user));
        } catch (Exception e) {
            throw new ApiException(FAIL_UPDATE_USER);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        boolean isExistUser = userRepository.existsById(id);

        if (!isExistUser) {
            throw new ApiException(NOT_EXIST_USER);
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(FAIL_DELETE_USER);
        }
    }

}
