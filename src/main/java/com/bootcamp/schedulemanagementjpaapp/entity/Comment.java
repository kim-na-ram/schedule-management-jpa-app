package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

@Getter
@Entity
@Builder
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void updateComment(CommentRequestDto updateCommentRequestDto) {
        if (StringUtils.hasText(updateCommentRequestDto.getContents())) {
            this.contents = updateCommentRequestDto.getContents();
        }
        if (StringUtils.hasText(updateCommentRequestDto.getUserName())) {
            this.regUserName = updateCommentRequestDto.getUserName();
        }
    }
}