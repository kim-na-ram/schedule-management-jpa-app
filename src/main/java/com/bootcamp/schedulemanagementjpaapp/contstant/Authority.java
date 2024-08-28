package com.bootcamp.schedulemanagementjpaapp.contstant;


import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.INVALID_USER_AUTHORITY;

@Getter
@AllArgsConstructor
public enum Authority {
    ADMIN("관리자"),
    USER("일반 사용자");

    private final String userRole;

    public static Authority from(String userRole) {
        for (Authority authority : Authority.values()) {
            if (authority.getUserRole().equals(userRole)) {
                return authority;
            }
        }
        throw new ApiException(INVALID_USER_AUTHORITY);
    }
}
