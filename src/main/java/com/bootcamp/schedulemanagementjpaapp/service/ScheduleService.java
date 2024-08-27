package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleFindResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto registerSchedule(ScheduleRequestDto registerScheduleReqDto);
    ScheduleResponseDto getSchedule(Long id);
    List<ScheduleFindResponseDto> getScheduleList(Pageable pageable);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updateScheduleReqDto);
    void deleteSchedule(Long id);
}