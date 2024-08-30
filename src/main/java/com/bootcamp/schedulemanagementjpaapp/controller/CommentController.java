package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.CommentRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.CommentResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.SUCCESS;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<?> registerComment(
            @Valid @RequestBody CommentRequestDto registerCommentRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        CommentResponseDto registerCommentResponseDto =
                commentService.registerComment(String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)), registerCommentRequestDto);
        return new ResponseEntity<>(registerCommentResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> getComment(
            @PathVariable("commentId") long commentId
    ) {
        CommentResponseDto commentResponseDto = commentService.getComment(commentId);
        return new ResponseEntity<>(commentResponseDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getCommentList() {
        List<CommentResponseDto> commentsResponseDto = commentService.getCommentList();
        return new ResponseEntity<>(commentsResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable("commentId") long commentId,
            @Valid @RequestBody CommentRequestDto updateCommentRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        CommentResponseDto commentResponseDto =
                commentService.updateComment(commentId, String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)), updateCommentRequestDto);
        return new ResponseEntity<>(commentResponseDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("commentId") long commentId,
            HttpServletRequest httpServletRequest
    ) {
        commentService.deleteComment(commentId, String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)));
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }

}