package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String name;
    private final String email;
    private final LocalDateTime regDate;
    private final LocalDateTime updateDate;

    @Builder
    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
    }
}
