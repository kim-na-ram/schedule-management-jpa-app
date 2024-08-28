package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.NOT_EXIST_SCHEDULE;

public interface ScheduleJPARepository extends JpaRepository<Schedule, Long> {
    default Schedule findScheduleById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));
    }
}