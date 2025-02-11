package com.example.schedulemanagerdevelop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotBlank(message = "이름은 필수값입니다.")
    private final String username;

    @NotBlank(message = "제목은 필수값입니다.")
    @Max(value = 15, message = "제목은 15자 이내여야 합니다.")
    private final String title;

    @NotBlank(message = "내용은 필수값입니다.")
    @Max(value = 100, message = "내용은 100자 이내여야 합니다.")
    private final String contents;

    public CreateScheduleRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
