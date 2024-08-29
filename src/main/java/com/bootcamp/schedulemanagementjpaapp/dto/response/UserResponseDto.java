package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private long userId;
    private String name;
    private String email;
    private String authority;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private UserResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.authority = user.getAuthority().getUserRole();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
    }

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user);
    }
}
