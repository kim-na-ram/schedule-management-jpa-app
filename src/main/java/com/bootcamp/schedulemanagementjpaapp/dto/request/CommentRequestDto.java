package com.bootcamp.schedulemanagementjpaapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequestDto {
    @NotNull(message = "{comment.scheduleId.null}")
    private Long scheduleId;
    @NotBlank(message = "{comment.contents.blank}")
    private String contents;
}
