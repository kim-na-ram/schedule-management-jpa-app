package com.bootcamp.schedulemanagementjpaapp.service.weather;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherService {
    String getTodayWeather() throws JsonProcessingException;
}
