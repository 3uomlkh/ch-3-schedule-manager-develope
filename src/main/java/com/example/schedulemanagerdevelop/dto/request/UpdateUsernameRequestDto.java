package com.example.schedulemanagerdevelop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUsernameRequestDto {

    @NotBlank(message = "이름은 필수값입니다.")
    @Size(min = 1, max = 10, message = "이름은 10자 이내여야 합니다.")
    private String username;

}