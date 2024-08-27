package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Manage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagerResponseDto {
    private Long managerId;
    private String managerName;
    private String managerEmail;

    public ManagerResponseDto(Manage mamager) {
        this.managerId = mamager.getUser().getId();
        this.managerName = mamager.getUser().getName();
        this.managerEmail = mamager.getUser().getEmail();
    }
}
