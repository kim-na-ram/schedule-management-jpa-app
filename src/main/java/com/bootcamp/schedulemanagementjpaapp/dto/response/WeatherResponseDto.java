package com.bootcamp.schedulemanagementjpaapp.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeatherResponseDto {
    private String date;
    private String weather;
}