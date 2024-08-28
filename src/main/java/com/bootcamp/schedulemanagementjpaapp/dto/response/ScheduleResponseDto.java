package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleResponseDto {
    private long scheduleId;
    private String title;
    private String contents;
    private String regDateWeather;
    private String userName;
    private String userEmail;
    private int commentCount;
    private List<ManagerResponseDto> managerList;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.regDateWeather = schedule.getWeather();
        this.userName = schedule.getUser().getName();
        this.userEmail = schedule.getUser().getEmail();
        this.commentCount = schedule.getComments().size();
        this.managerList = schedule.getManagers().stream().map(ManagerResponseDto::from).toList();
        this.regDate = schedule.getRegDate();
        this.updateDate = schedule.getUpdateDate();
    }

    public static ScheduleResponseDto from(Schedule schedule) {
        return new ScheduleResponseDto(schedule);
    }
}