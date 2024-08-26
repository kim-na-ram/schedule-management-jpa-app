package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRegisterResponseDto {
    private final Long userId;
    private final String name;
    private final String email;
    private final LocalDateTime regDate;
    private final LocalDateTime updateDate;
    private final String accessToken;

    @Builder
    public UserRegisterResponseDto(User user, String accessToken) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
        this.accessToken = accessToken;
    }
}
