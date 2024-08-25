package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

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
