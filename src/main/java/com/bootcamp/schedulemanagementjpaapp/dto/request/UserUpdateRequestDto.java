package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateRequestDto {
    private String name;
    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;
}