package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.SchedulesResponseDto;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    ScheduleResponseDto registerSchedule(ScheduleRequestDto registerScheduleReqDto);
    ScheduleResponseDto getSchedule(Long id);
    SchedulesResponseDto getAllSchedules(Pageable pageable);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updateScheduleReqDto);
}
