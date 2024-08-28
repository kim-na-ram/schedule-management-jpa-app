package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final Long scheduleId;
    private final String userName;
    private final String content;
    private final LocalDateTime regDate;
    private final LocalDateTime updateDate;

    @Builder
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.scheduleId = comment.getSchedule().getId();
        this.userName = comment.getRegUserName();
        this.content = comment.getContents();
        this.regDate = comment.getRegDate();
        this.updateDate = comment.getUpdateDate();
    }
}
