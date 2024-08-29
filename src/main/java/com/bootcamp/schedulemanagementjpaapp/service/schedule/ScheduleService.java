package com.bootcamp.schedulemanagementjpaapp.service.schedule;

import com.bootcamp.schedulemanagementjpaapp.common.enums.Authority;
import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleFindResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto registerSchedule(String email, ScheduleRegisterRequestDto scheduleRegisterRequestDto);
    ScheduleResponseDto getSchedule(Long id);
    List<ScheduleFindResponseDto> getScheduleList(Pageable pageable);
    ScheduleResponseDto updateSchedule(Long id, Authority authority, ScheduleUpdateRequestDto scheduleUpdateRequestDto);
    void deleteSchedule(Long id, Authority authority);
}