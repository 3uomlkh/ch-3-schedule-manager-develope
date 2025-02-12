package com.example.schedulemanagerdevelop.dto.response;

import com.example.schedulemanagerdevelop.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String contents;
    private final String memberName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.memberName = comment.getMember().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
