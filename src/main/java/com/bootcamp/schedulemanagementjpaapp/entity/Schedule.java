package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Schedule extends BaseEntity {
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

    @NotNull
    @Column
    private String weather;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Manage> managers;

    private Schedule(User user, String title, String contents, String weather) {
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.weather = weather;
        this.managers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public static Schedule dtoToEntity(User user, ScheduleRequestDto scheduleRequestDto, String weather) {
        return new Schedule(
                user,
                scheduleRequestDto.getTitle(),
                scheduleRequestDto.getContents(),
                weather
        );
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
