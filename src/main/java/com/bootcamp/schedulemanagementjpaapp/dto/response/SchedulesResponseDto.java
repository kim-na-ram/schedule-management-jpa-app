package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class SchedulesResponseDto {
    private final List<ScheduleVO> scheduleList;

    @Getter
    public static class ScheduleVO {
        private final Long scheduleId;
        private final String title;
        private final String contents;
        private final Long userId;
        private final String userName;
        private final String userEmail;
        private final Integer commentCount;
        private final LocalDateTime regDate;
        private final LocalDateTime updateDate;

        @Builder
        public ScheduleVO(Schedule schedule) {
            this.scheduleId = schedule.getId();
            this.title = schedule.getTitle();
            this.contents = schedule.getContents();
            this.userId = schedule.getUser().getId();
            this.userName = schedule.getUser().getName();
            this.userEmail = schedule.getUser().getEmail();
            this.commentCount = schedule.getComments().size();
            this.regDate = schedule.getRegDate();
            this.updateDate = schedule.getUpdateDate();
        }
    }
}
