package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Manage;
import lombok.Getter;

@Getter
public class ManagerResponseDto {
    private final Long managerId;
    private final String managerName;
    private final String managerEmail;

    public ManagerResponseDto(Manage mamager) {
        this.managerId = mamager.getUser().getId();
        this.managerName = mamager.getUser().getName();
        this.managerEmail = mamager.getUser().getEmail();
    }
}
