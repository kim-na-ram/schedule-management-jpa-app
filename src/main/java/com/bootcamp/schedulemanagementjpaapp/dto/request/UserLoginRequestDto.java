package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginRequestDto {
    @NotBlank(message = "{auth.login.email.blank}")
    private String email;
    @NotBlank(message = "{auth.login.password.blank}")
    private String password;
}