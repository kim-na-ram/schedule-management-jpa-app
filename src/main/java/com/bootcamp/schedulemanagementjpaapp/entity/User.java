package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.dto.request.UserRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.UserUpdateRequestDto;
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
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @NotNull
    @Column
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Manage> manages;

    private User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.schedules = new ArrayList<>();
        this.manages = new ArrayList<>();
    }

    public static User dtoToEntity(String encryptPassword, UserRegisterRequestDto userRegisterRequestDto) {
        return new User(userRegisterRequestDto.getName(), userRegisterRequestDto.getEmail(), encryptPassword);
    }

    public void addSchedules(List<Schedule> schedules) {
        for (Schedule schedule : schedules) {
            if (!this.schedules.contains(schedule)) {
                this.schedules.add(schedule);
                schedule.setUser(this);
            }
        }
    }

    public void addManages(List<Manage> manages) {
        for (Manage manage : manages) {
            if (!this.manages.contains(manage)) {
                this.manages.add(manage);
                manage.setUser(this);
            }
        }
    }

    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        if (StringUtils.hasText(userUpdateRequestDto.getName())) {
            this.name = userUpdateRequestDto.getName();
        }
        if (StringUtils.hasText(userUpdateRequestDto.getEmail())) {
            this.email = userUpdateRequestDto.getEmail();
        }
    }
}
