package com.bootcamp.schedulemanagementjpaapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeatherResponseDto {
    private String date;
    private String weather;
}