package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Schedule extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String contents;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments;

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getSchedule() != this) {
            comment.setSchedule(this);
        }
    }

    public void updateSchedule(ScheduleRequestDto updateScheduleRequestDto) {
        if(StringUtils.hasText(updateScheduleRequestDto.getTitle())) {
            this.title = updateScheduleRequestDto.getTitle();
        }
        if(StringUtils.hasText(updateScheduleRequestDto.getContents())) {
            this.contents = updateScheduleRequestDto.getContents();
        }
        if(StringUtils.hasText(updateScheduleRequestDto.getUserName())) {
            this.userName = updateScheduleRequestDto.getUserName();
        }
    }
}
