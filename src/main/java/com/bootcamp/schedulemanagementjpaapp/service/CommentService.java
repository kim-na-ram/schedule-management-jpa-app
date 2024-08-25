package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentsResponseDto;

public interface CommentService {
    CommentResponseDto registerComment(Long scheduleId, CommentRequestDto registerCommentRequestDto);
    CommentResponseDto getComment(Long id, Long scheduleId);
    CommentsResponseDto getAllComment(Long id);
    CommentResponseDto updateComment(Long id, Long scheduleId, CommentRequestDto updateCommentRequestDto);
    void deleteComment(Long id, Long scheduleId);
}