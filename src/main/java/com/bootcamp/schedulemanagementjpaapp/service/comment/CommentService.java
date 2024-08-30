package com.bootcamp.schedulemanagementjpaapp.service.comment;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.User;

import java.util.List;

public interface CommentService {
    CommentResponseDto registerComment(String email, CommentRequestDto registerCommentRequestDto);
    CommentResponseDto getComment(Long id);
    List<CommentResponseDto> getCommentList();
    CommentResponseDto updateComment(Long id, String email, CommentRequestDto updateCommentRequestDto);
    void deleteComment(Long id, String email);
}