package com.bootcamp.schedulemanagementjpaapp.dto.request;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Builder
public class UserRegisterRequestDto {
    @NotBlank(message = "이름은 필수입니다.")
    private final String name;
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "잘못된 이메일 형식입니다.")
    private final String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8자에서 최대 16자여야 합니다.")
    private final String password;

    public User toEntity(String password) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .manages(new ArrayList<>())
                .build();
    }
}
