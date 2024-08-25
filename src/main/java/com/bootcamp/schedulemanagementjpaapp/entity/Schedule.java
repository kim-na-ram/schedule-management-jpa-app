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
    @ManyToOne
    @JoinColumn(name = "reg_user_id")
    private User user;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String contents;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Manage> managers;

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getSchedules().remove(this);
        }
        this.user = user;
        user.getSchedules().add(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getSchedule() != this) {
            comment.setSchedule(this);
        }
    }

    public void addManagers(List<Manage> managers) {
        for (Manage manager : managers) {
            if (!this.managers.contains(manager)) {
                this.managers.add(manager);
                manager.setSchedule(this);
            }
        }
    }

    public void updateSchedule(ScheduleRequestDto updateScheduleRequestDto) {
        if(StringUtils.hasText(updateScheduleRequestDto.getTitle())) {
            this.title = updateScheduleRequestDto.getTitle();
        }
        if(StringUtils.hasText(updateScheduleRequestDto.getContents())) {
            this.contents = updateScheduleRequestDto.getContents();
        }
    }
}
