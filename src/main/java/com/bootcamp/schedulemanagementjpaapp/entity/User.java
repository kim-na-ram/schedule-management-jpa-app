package com.bootcamp.schedulemanagementjpaapp.entity;

import com.bootcamp.schedulemanagementjpaapp.common.enums.Authority;
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

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Manage> manageList;

    private User(String name, String email, String password, Authority authority) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.scheduleList = new ArrayList<>();
        this.commentList = new ArrayList<>();
        this.manageList = new ArrayList<>();
    }

    public static User dtoToEntity(String encryptPassword, UserRegisterRequestDto userRegisterRequestDto) {
        return new User(userRegisterRequestDto.getName(), userRegisterRequestDto.getEmail(), encryptPassword, Authority.from(userRegisterRequestDto.getAuthority()));
    }

    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        if (StringUtils.hasText(userUpdateRequestDto.getName())) {
            this.name = userUpdateRequestDto.getName();
        }
        if (StringUtils.hasText(userUpdateRequestDto.getAuthority())) {
            this.authority = Authority.from(userUpdateRequestDto.getAuthority());
        }
    }
}
