package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateRequestDto {
    private final String name;
    @Email(message = "잘못된 이메일 형식입니다.")
    private final String email;
}
