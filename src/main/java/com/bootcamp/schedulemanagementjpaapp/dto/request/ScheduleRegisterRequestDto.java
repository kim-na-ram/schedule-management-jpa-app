package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRegisterRequestDto {
    @NotBlank(message = "{schedule.title.blank}")
    private String title;
    @NotBlank(message = "{schedule.contents.blank}")
    private String contents;
}