package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final String userName;
    private final LocalDateTime regDate;
    private final LocalDateTime updateDate;

    @Builder
    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.userName = schedule.getUserName();
        this.regDate = schedule.getRegDate();
        this.updateDate = schedule.getUpdateDate();
    }
}