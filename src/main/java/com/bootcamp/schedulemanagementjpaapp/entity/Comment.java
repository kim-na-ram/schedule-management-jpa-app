package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @NotNull
    @Column(name = "reg_user_name")
    private String regUserName;

    @NotNull
    @Column
    private String contents;

    private Comment(Schedule schedule, String regUserName, String contents) {
        this.schedule = schedule;
        this.regUserName = regUserName;
        this.contents = contents;
    }

    public static Comment dtoDoEntity(Schedule schedule, CommentRequestDto commentRequestDto) {
        return new Comment(
                schedule,
                commentRequestDto.getUserName(),
                commentRequestDto.getContents()
        );
    }

    public void updateComment(CommentRequestDto updateCommentRequestDto) {
        if (StringUtils.hasText(updateCommentRequestDto.getContents())) {
            this.contents = updateCommentRequestDto.getContents();
        }
        if (StringUtils.hasText(updateCommentRequestDto.getUserName())) {
            this.regUserName = updateCommentRequestDto.getUserName();
        }
    }
}