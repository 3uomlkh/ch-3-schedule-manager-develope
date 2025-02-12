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

    public CommentResponseDto(Long id, String contents, String memberName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents = contents;
        this.memberName = memberName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponseDto of(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContents(),
                comment.getMember().getUsername(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
