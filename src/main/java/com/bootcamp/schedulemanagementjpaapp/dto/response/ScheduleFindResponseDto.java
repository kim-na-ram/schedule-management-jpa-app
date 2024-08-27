package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleFindResponseDto {
    private Long scheduleId;
    private String title;
    private String contents;
    private String userName;
    private String userEmail;
    private Integer commentCount;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ScheduleFindResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.userName = schedule.getUser().getName();
        this.userEmail = schedule.getUser().getEmail();
        this.commentCount = schedule.getComments().size();
        this.regDate = schedule.getRegDate();
        this.updateDate = schedule.getUpdateDate();
    }
}
