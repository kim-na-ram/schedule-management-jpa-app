package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.config.PasswordEncoder;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import com.bootcamp.schedulemanagementjpaapp.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserJPARepository userRepository;

    @Override
    @Transactional
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        String encryptPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
        try {
            User user = userRepository.save(User.dtoToEntity(encryptPassword, userRegisterRequestDto));
            String accessToken = jwtUtil.createAccessToken(userRegisterRequestDto.getEmail());

            return new UserRegisterResponseDto(user, accessToken);
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
    public List<UserResponseDto> getUserList() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(UserResponseDto::new)
                    .toList();
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_USER);
        }
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(NOT_EXIST_USER));

        try {
            user.updateUser(userUpdateRequestDto);
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
