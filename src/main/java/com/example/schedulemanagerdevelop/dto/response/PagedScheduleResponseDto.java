package com.example.schedulemanagerdevelop.dto.response;

import com.example.schedulemanagerdevelop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PagedScheduleResponseDto {
    private final String title;
    private final String contents;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    public PagedScheduleResponseDto(String title, String contents, int commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
    }

    public static PagedScheduleResponseDto of(Schedule schedule) {
        return new PagedScheduleResponseDto(
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getComments().size(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getMember().getUsername()
        );
    }
}
