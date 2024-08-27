package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterResponseDto {
    private Long userId;
    private String name;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String accessToken;

    public UserRegisterResponseDto(User user, String accessToken) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
        this.accessToken = accessToken;
    }
}
