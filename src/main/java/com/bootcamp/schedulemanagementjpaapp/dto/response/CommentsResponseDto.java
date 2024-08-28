package com.bootcamp.schedulemanagementjpaapp.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentsResponseDto {
    private final List<CommentResponseDto> commentList;
}
