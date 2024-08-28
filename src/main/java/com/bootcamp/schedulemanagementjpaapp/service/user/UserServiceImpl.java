package com.bootcamp.schedulemanagementjpaapp.service.user;

import com.bootcamp.schedulemanagementjpaapp.common.config.PasswordEncoder;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserLoginRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserLoginResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserRegisterResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.UserResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import com.bootcamp.schedulemanagementjpaapp.common.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserJPARepository userRepository;

    @Override
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        boolean isExistUser = userRepository.existsByEmail(userRegisterRequestDto.getEmail());

        if(isExistUser) {
            throw new ApiException(DUPLICATE_USER_EMAIL);
        }

        String encryptPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
        User user = User.dtoToEntity(encryptPassword, userRegisterRequestDto);

        try {
            User savedUser = userRepository.save(user);
            String accessToken =
                    jwtUtil.createAccessToken(userRegisterRequestDto.getEmail(), userRegisterRequestDto.getAuthority());

            return UserRegisterResponseDto.of(savedUser, accessToken);
        } catch (Exception e) {
            throw new ApiException(FAIL_REGISTER_USER);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(() -> new ApiException(WRONG_EMAIL_OR_PASSWORD));

        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new ApiException(WRONG_EMAIL_OR_PASSWORD);
        }

        try {
            String accessToken =
                    jwtUtil.createAccessToken(userLoginRequestDto.getEmail(), user.getAuthority().getUserRole());
            return UserLoginResponseDto.of(accessToken);
        } catch (Exception e) {
            throw new ApiException(FAIL_LOGIN_USER);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUser(String email) {
        User user = userRepository.findUserByEmail(email);

        try {
            return UserResponseDto.from(user);
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_USER);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUserList() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(UserResponseDto::from)
                    .toList();
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_USER);
        }
    }

    @Override
    public UserResponseDto updateUser(String email, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findUserByEmail(email);

        try {
            user.updateUser(userUpdateRequestDto);
            return UserResponseDto.from(userRepository.save(user));
        } catch (Exception e) {
            throw new ApiException(FAIL_UPDATE_USER);
        }
    }

    @Override
    public void deleteUser(String email) {
        userRepository.existsUserByEmail(email);

        try {
            userRepository.deleteByEmail(email);
        } catch (Exception e) {
            throw new ApiException(FAIL_DELETE_USER);
        }
    }

}
