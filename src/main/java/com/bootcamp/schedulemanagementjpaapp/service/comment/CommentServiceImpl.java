package com.bootcamp.schedulemanagementjpaapp.service.comment;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.entity.Comment;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
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
    public CommentResponseDto registerComment(String email, CommentRequestDto registerCommentRequestDto) {
        Schedule schedule = scheduleRepository.findById(registerCommentRequestDto.getScheduleId())
                .orElseThrow(() -> new ApiException(NOT_EXIST_SCHEDULE));

        User user = userRepository.findUserByEmail(email);

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
    public CommentResponseDto getComment(Long id) {
        Comment comment = commentRepository.findCommentById(id);

        try {
            return CommentResponseDto.from(comment);
        } catch (ApiException e) {
            throw new ApiException(FAIL_GET_COMMENT);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentList() {
        try {
            return commentRepository.findAll().stream().map(CommentResponseDto::from).toList();
        } catch (ApiException e) {
            throw new ApiException(FAIL_GET_COMMENT);
        }
    }

    @Override
    public CommentResponseDto updateComment(Long id, String email, CommentRequestDto updateCommentRequestDto) {
        Comment comment = commentRepository.findCommentById(id);

        if(!comment.getUser().getEmail().equals(email)) {
            throw new ApiException(UNAUTHORIZED_UPDATE_OR_DELETE_COMMENT);
        }

        try {
            comment.updateComment(updateCommentRequestDto);
            return CommentResponseDto.from(commentRepository.save(comment));
        } catch (ApiException e) {
            throw new ApiException(FAIL_UPDATE_COMMENT);
        }
    }

    @Override
    public void deleteComment(Long id, String email) {
        Comment comment = commentRepository.findCommentById(id);

        if(!comment.getUser().getEmail().equals(email)) {
            throw new ApiException(UNAUTHORIZED_UPDATE_OR_DELETE_COMMENT);
        }

        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(FAIL_DELETE_COMMENT);
        }
    }
}