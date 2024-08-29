package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.SUCCESS;

@RestController
@RequestMapping("/api/schedules/{scheduleId}")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<?> registerComment(
            @PathVariable("scheduleId") Long scheduleId,
            @RequestBody CommentRequestDto registerCommentRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        CommentResponseDto registerCommentResponseDto =
                commentService.registerComment(scheduleId, String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)), registerCommentRequestDto);
        return new ResponseEntity<>(registerCommentResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> getComment(
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("commentId") Long commentId
    ) {
        CommentResponseDto commentResponseDto = commentService.getComment(commentId, scheduleId);
        return new ResponseEntity<>(commentResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getCommentList(@PathVariable("scheduleId") Long scheduleId) {
        List<CommentResponseDto> commentsResponseDto = commentService.getCommentList(scheduleId);
        return new ResponseEntity<>(commentsResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequestDto updateCommentRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        CommentResponseDto commentResponseDto =
                commentService.updateComment(commentId, scheduleId, String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)), updateCommentRequestDto);
        return new ResponseEntity<>(commentResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("commentId") Long commentId,
            HttpServletRequest httpServletRequest
    ) {
        commentService.deleteComment(commentId, scheduleId, String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)));
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }

}