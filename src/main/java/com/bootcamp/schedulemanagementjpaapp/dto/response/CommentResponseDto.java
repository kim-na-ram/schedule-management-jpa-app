package com.bootcamp.schedulemanagementjpaapp.dto.response;

import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private Long id;
    private Long scheduleId;
    private String userName;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.scheduleId = comment.getSchedule().getId();
        this.userName = comment.getRegUserName();
        this.content = comment.getContents();
        this.regDate = comment.getRegDate();
        this.updateDate = comment.getUpdateDate();
    }
}
