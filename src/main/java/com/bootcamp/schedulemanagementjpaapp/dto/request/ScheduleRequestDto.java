package com.bootcamp.schedulemanagementjpaapp.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private List<String> managerList;
}