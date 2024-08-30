package com.bootcamp.schedulemanagementjpaapp.service.schedule;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleFindResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.repository.ScheduleJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import com.bootcamp.schedulemanagementjpaapp.service.manage.ManageService;
import com.bootcamp.schedulemanagementjpaapp.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ManageService manageService;
    private final WeatherService weatherService;

    private final ScheduleJPARepository scheduleRepository;
    private final UserJPARepository userRepository;

    @Override
    public ScheduleResponseDto registerSchedule(String email, ScheduleRegisterRequestDto scheduleRegisterRequestDto) {
        User user = userRepository.findUserByEmail(email);

        try {
            String weather = weatherService.getTodayWeather();
            Schedule result = scheduleRepository.save(Schedule.dtoToEntity(user, scheduleRegisterRequestDto, weather));
            return ScheduleResponseDto.from(result);
        } catch (Exception e) {
            throw new ApiException(FAIL_REGISTER_SCHEDULE);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);

        try {
            return ScheduleResponseDto.from(schedule);
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_SCHEDULE);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleFindResponseDto> getScheduleList(Pageable pageable) {
        try {
            Page<Schedule> pages = scheduleRepository.findAll(pageable);

            return pages.getContent().stream().map(ScheduleFindResponseDto::from).toList();
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_SCHEDULE);
        }
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findScheduleById(id);

        Set<String> emailSet = Set.copyOf(scheduleUpdateRequestDto.getManagerList());
        if(!emailSet.isEmpty()) {
            manageService.addManagerList(emailSet, schedule);
        }

        try {
            schedule.updateSchedule(scheduleUpdateRequestDto);
            return ScheduleResponseDto.from(scheduleRepository.save(schedule));
        } catch (Exception e) {
            throw new ApiException(FAIL_UPDATE_SCHEDULE);
        }
    }

    @Override
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