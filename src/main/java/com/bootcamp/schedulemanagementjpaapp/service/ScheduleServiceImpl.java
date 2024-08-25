package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.SchedulesResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.ScheduleJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleJPARepository scheduleRepository;

    @Override
    @Transactional
    public ScheduleResponseDto registerSchedule(ScheduleRequestDto registerScheduleReqDto) {
        Schedule schedule = registerScheduleReqDto.toEntity();

        try {
            Schedule result = scheduleRepository.save(schedule);
            return new ScheduleResponseDto(result);
        } catch (Exception e) {
            throw new ApiException(FAIL_REGISTER_SCHEDULE);
        }
    }

    @Override
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));

        try {
            return new ScheduleResponseDto(schedule);
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_SCHEDULE);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SchedulesResponseDto getAllSchedules(Pageable pageable) {
        try {
            Page<Schedule> pages = scheduleRepository.findAll(pageable);
            List<ScheduleResponseDto> schedules = new ArrayList<>();
            pages.getContent()
                    .forEach(schedule -> schedules.add(new ScheduleResponseDto(schedule)));

            return SchedulesResponseDto.builder().scheduleList(schedules).build();
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_SCHEDULE);
        }
    }

    @Override
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updateScheduleReqDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));

        try {
            schedule.updateSchedule(updateScheduleReqDto);
            return new ScheduleResponseDto(scheduleRepository.save(schedule));
        } catch (Exception e) {
            throw new ApiException(FAIL_UPDATE_SCHEDULE);
        }
    }
}