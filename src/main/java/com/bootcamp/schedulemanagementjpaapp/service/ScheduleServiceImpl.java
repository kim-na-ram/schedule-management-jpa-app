package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleFindResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.Manage;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.ManageJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.ScheduleJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleJPARepository scheduleRepository;
    private final ManageJPARepository manageRepository;
    private final UserJPARepository userRepository;

    @Override
    @Transactional
    public ScheduleResponseDto registerSchedule(ScheduleRequestDto registerScheduleReqDto) {
        User user = userRepository.findById(registerScheduleReqDto.getUserId())
                .orElseThrow(() -> new ApiException(NOT_EXIST_USER));

        try {
            Schedule result = scheduleRepository.save(Schedule.dtoToEntity(user, registerScheduleReqDto));
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
    public List<ScheduleFindResponseDto> getScheduleList(Pageable pageable) {
        try {
            Page<Schedule> pages = scheduleRepository.findAll(pageable);

            return pages.getContent().stream().map(ScheduleFindResponseDto::new).toList();
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_SCHEDULE);
        }
    }

    @Override
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updateScheduleReqDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));

        for (String managerEmail : updateScheduleReqDto.getManagerList()) {
            User user = userRepository.findByEmail(managerEmail)
                    .orElseThrow(() -> new ApiException(NOT_EXIST_USER));
            try {
                manageRepository.save(new Manage(schedule, user));
            } catch (Exception e) {
                throw new ApiException(FAIL_UPDATE_SCHEDULE);
            }
        }

        try {
            schedule.updateSchedule(updateScheduleReqDto);
            return new ScheduleResponseDto(scheduleRepository.save(schedule));
        } catch (Exception e) {
            throw new ApiException(FAIL_UPDATE_SCHEDULE);
        }
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        boolean isExistSchedule = scheduleRepository.existsById(id);

        if (!isExistSchedule) {
            throw new ApiException(NOT_EXIST_SCHEDULE);
        }

        try {
            scheduleRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(FAIL_DELETE_COMMENT);
        }
    }
}