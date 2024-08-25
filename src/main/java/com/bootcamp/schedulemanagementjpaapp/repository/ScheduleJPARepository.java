package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJPARepository extends JpaRepository<Schedule, Long> {
}