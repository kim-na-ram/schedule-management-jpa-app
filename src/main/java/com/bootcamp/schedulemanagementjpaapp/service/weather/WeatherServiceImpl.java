package com.bootcamp.schedulemanagementjpaapp.service.weather;

import com.bootcamp.schedulemanagementjpaapp.dto.WeatherResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;

    public String getTodayWeather() throws JsonProcessingException {
        String url = "https://f-api.github.io/f-api/weather.json";
        ResponseEntity<?> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<WeatherResponseDto> parsingList =
                objectMapper.readValue(String.valueOf(response.getBody()), new TypeReference<>() {});
        Map<String, String> weatherMap = parsingList.stream().collect(
                Collectors.toMap(WeatherResponseDto::getDate, WeatherResponseDto::getWeather));

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        return weatherMap.get(dateFormat.format(new Date()));
    }

}
