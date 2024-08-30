package com.bootcamp.schedulemanagementjpaapp.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginResponseDto {
    private String accessToken;

    private UserLoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public static UserLoginResponseDto of(String accessToken) {
        return new UserLoginResponseDto(accessToken);
    }
}
