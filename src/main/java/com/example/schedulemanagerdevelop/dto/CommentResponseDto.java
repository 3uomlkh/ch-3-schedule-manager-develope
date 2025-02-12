package com.example.schedulemanagerdevelop.dto;

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

    public CommentResponseDto(Long id, String contents, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents = contents;
        this.memberName = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.memberName = comment.getMember().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
