package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleJPARepository extends JpaRepository<Schedule, Long> {
    boolean existsByIdAndUser_Email(Long id, String email);
    Optional<Schedule> findByIdAndUser_Email(Long id, String email);
}