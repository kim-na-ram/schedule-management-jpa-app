package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterRequestDto {
    @NotBlank(message = "{auth.join.name.blank}")
    private String name;
    @NotBlank(message = "{auth.join.email.blank}")
    @Email(message = "{auth.join.email.wrong_format}")
    private String email;
    @NotBlank(message = "{auth.join.password.blank}")
    @Size(min = 8, max = 16, message = "{auth.join.password.size}")
    private String password;
    private String authority;
}
