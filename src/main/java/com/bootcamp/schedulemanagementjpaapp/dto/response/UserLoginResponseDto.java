package com.bootcamp.schedulemanagementjpaapp.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginResponseDto {
    private String accessToken;

    public UserLoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
