package com.bootcamp.schedulemanagementjpaapp.service.comment;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto registerComment(Long scheduleId, CommentRequestDto registerCommentRequestDto);
    CommentResponseDto getComment(Long id, Long scheduleId);
    List<CommentResponseDto> getCommentList(Long id);
    CommentResponseDto updateComment(Long id, Long scheduleId, CommentRequestDto updateCommentRequestDto);
    void deleteComment(Long id, Long scheduleId);
}