package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginRequestDto {
    @NotBlank(message = "로그인 시, 이메일은 필수입니다.")
    private final String email;
    @NotBlank(message = "로그인 시, 비밀번호는 필수입니다.")
    private final String password;
}