package com.bootcamp.schedulemanagementjpaapp.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequestDto {
    private String contents;
    private String userName;
}
