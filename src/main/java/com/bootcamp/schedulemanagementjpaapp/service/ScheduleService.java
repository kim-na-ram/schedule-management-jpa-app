package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;

public interface ScheduleService {
    ScheduleResponseDto registerSchedule(ScheduleRequestDto registerScheduleReqDto);
    ScheduleResponseDto getSchedule(Long id);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updateScheduleReqDto);
}
