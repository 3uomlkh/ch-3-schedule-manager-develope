package com.example.schedulemanagerdevelop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotBlank(message = "제목은 필수값입니다.")
    @Size(min = 1, max = 15, message = "제목은 15자 이내여야 합니다.")
    private final String title;

    @NotBlank(message = "내용은 필수값입니다.")
    @Size(min = 1, max = 100, message = "내용은 100자 이내여야 합니다.")
    private final String contents;

    public CreateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
