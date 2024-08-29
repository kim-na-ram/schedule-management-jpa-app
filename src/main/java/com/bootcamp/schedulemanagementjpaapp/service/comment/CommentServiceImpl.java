package com.bootcamp.schedulemanagementjpaapp.service.comment;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.repository.CommentJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.ScheduleJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentJPARepository commentRepository;
    private final ScheduleJPARepository scheduleRepository;
    private final UserJPARepository userRepository;

    @Override
    public CommentResponseDto registerComment(Long scheduleId, String email, CommentRequestDto registerCommentRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(NOT_EXIST_USER));

        try {
            Comment comment = Comment.dtoDoEntity(schedule, user, registerCommentRequestDto);
            Comment savedComment = commentRepository.save(comment);

            return CommentResponseDto.from(savedComment);
        } catch (ApiException e) {
            throw new ApiException(FAIL_REGISTER_COMMENT);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long id, Long scheduleId) {
        Comment comment = commentRepository.findByIdAndSchedule_Id(id, scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_COMMENT));
        try {
            return CommentResponseDto.from(comment);
        } catch (ApiException e) {
            throw new ApiException(FAIL_GET_COMMENT);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentList(Long scheduleId) {
        try {
            return commentRepository.findAllBySchedule_Id(scheduleId).stream().map(CommentResponseDto::from).toList();
        } catch (ApiException e) {
            throw new ApiException(FAIL_GET_COMMENT);
        }
    }

    @Override
    public CommentResponseDto updateComment(Long id, Long scheduleId, String email, CommentRequestDto updateCommentRequestDto) {
        Comment comment = commentRepository.findByIdAndSchedule_Id(id, scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_COMMENT));

        if(!comment.getUser().getEmail().equals(email)) {
            throw new ApiException(UNAUTHORIZED_UPDATE_COMMENT);
        }

        try {
            comment.updateComment(updateCommentRequestDto);
            return CommentResponseDto.from(commentRepository.save(comment));
        } catch (ApiException e) {
            throw new ApiException(FAIL_UPDATE_COMMENT);
        }
    }

    @Override
    public void deleteComment(Long id, Long scheduleId, String email) {
        Comment comment = commentRepository.findByIdAndSchedule_Id(id, scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_COMMENT));

        if(!comment.getUser().getEmail().equals(email)) {
            throw new ApiException(UNAUTHORIZED_DELETE_COMMENT);
        }

        try {
            commentRepository.deleteByIdAndSchedule_Id(id, scheduleId);
        } catch (Exception e) {
            throw new ApiException(FAIL_DELETE_COMMENT);
        }
    }
}