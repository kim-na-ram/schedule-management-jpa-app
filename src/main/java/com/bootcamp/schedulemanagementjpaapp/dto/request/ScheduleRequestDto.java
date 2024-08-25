package com.bootcamp.schedulemanagementjpaapp.dto.request;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import lombok.*;

@Getter
@Builder
public class ScheduleRequestDto {
    private final String title;
    private final String contents;
    private final String userName;

    public Schedule toEntity() {
        return Schedule.builder()
                .title(title)
                .contents(contents)
                .userName(userName)
                .build();
    }
}