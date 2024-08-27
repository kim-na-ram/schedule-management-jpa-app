package com.bootcamp.schedulemanagementjpaapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "manage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Manage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Manage(Schedule schedule, User user) {
        this.schedule = schedule;
        this.user = user;
    }

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getManages().remove(this);
        }
        this.user = user;
        user.getManages().add(this);
    }

    public void setSchedule(Schedule schedule) {
        if (this.schedule != null) {
            this.schedule.getManagers().remove(this);
        }
        this.schedule = schedule;
        schedule.getManagers().add(this);
    }
}
