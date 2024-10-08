package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterResponseDto {
    private long userId;
    private String name;
    private String email;
    private String authority;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String accessToken;

    private UserRegisterResponseDto(User user, String accessToken) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.authority = user.getAuthority().getUserRole();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
        this.accessToken = accessToken;
    }

    public static UserRegisterResponseDto of(User user, String accessToken) {
        return new UserRegisterResponseDto(user, accessToken);
    }
}
