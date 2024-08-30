package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.NOT_EXIST_COMMENT;

public interface CommentJPARepository extends JpaRepository<Comment, Long> {
    default Comment findCommentById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(NOT_EXIST_COMMENT));
    }
}