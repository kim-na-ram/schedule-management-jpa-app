package com.bootcamp.schedulemanagementjpaapp.dto.request;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Builder
public class UserRequestDto {
    private final String name;
    @Email(message = "잘못된 이메일 형식입니다.")
    private final String email;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .manages(new ArrayList<>())
                .build();
    }
}
