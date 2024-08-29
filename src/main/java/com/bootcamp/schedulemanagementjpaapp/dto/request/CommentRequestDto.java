package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String contents;
}
