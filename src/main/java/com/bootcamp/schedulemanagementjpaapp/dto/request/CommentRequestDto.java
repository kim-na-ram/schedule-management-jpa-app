package com.bootcamp.schedulemanagementjpaapp.dto.request;

import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDto {
    private final String contents;
    private final String userName;

    public Comment toEntity(Schedule schedule) {
        return Comment.builder()
                .schedule(schedule)
                .regUserName(userName)
                .contents(contents)
                .build();
    }
}
