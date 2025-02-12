package com.example.schedulemanagerdevelop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글 내용은 필수 입력 사항입니다.")
    private String contents;

}
