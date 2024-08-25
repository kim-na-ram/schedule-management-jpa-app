package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentsResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.CommentJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.ScheduleJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ScheduleJPARepository scheduleRepository;
    private final CommentJPARepository commentRepository;

    @Override
    @Transactional
    public CommentResponseDto registerComment(Long scheduleId, CommentRequestDto registerCommentRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));

        try {
            Comment comment = registerCommentRequestDto.toEntity(schedule);
            Comment result = commentRepository.save(comment);

            return new CommentResponseDto(result);
        } catch (ApiException e) {
            throw new ApiException(FAIL_REGISTER_COMMENT);
        }
    }

    @Override
    public CommentResponseDto getComment(Long id, Long scheduleId) {
        Comment comment = commentRepository.findByIdAndSchedule_Id(id, scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_COMMENT));
        try {
            return new CommentResponseDto(comment);
        } catch (ApiException e) {
            throw new ApiException(FAIL_GET_COMMENT);
        }
    }

    @Override
    public CommentsResponseDto getAllComment(Long scheduleId) {
        try {
            return CommentsResponseDto.builder().commentList(commentRepository.findAllBySchedule_Id(scheduleId).stream()
                    .map(CommentResponseDto::new).toList()).build();
        } catch (ApiException e) {
            throw new ApiException(FAIL_GET_COMMENT);
        }
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long id, Long scheduleId, CommentRequestDto updateCommentRequestDto) {
        Comment comment = commentRepository.findByIdAndSchedule_Id(id, scheduleId)
                .orElseThrow(() -> new ApiException(NOT_EXIST_COMMENT));

        try {
            comment.updateComment(updateCommentRequestDto);
            return new CommentResponseDto(commentRepository.save(comment));
        } catch (ApiException e) {
            throw new ApiException(FAIL_UPDATE_COMMENT);
        }
    }

    @Override
    @Transactional
    public void deleteComment(Long id, Long scheduleId) {
        boolean isExistComment = commentRepository.findByIdAndSchedule_Id(id, scheduleId).isPresent();

        if (!isExistComment) {
            throw new ApiException(NOT_EXIST_COMMENT);
        }

        try {
            commentRepository.deleteByIdAndSchedule_Id(id, scheduleId);
        } catch (Exception e) {
            throw new ApiException(FAIL_DELETE_COMMENT);
        }
    }
}