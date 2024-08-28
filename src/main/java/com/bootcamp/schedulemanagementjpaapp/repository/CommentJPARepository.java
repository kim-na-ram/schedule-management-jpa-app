package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentJPARepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndSchedule_Id(Long id, Long scheduleId);
    List<Comment> findAllBySchedule_Id(Long scheduleId);
    void deleteByIdAndSchedule_Id(Long id, Long scheduleId);
}