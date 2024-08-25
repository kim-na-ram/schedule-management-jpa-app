package com.bootcamp.schedulemanagementjpaapp.dto.request;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ScheduleRequestDto {
    private final String title;
    private final String contents;
    private final Long userId;
    private final List<Long> managerList;

    public Schedule toEntity(User user) {
        return Schedule.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .comments(new ArrayList<>())
                .managers(new ArrayList<>())
                .build();
    }
}