package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Long userId;
    private String name;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
    }
}
