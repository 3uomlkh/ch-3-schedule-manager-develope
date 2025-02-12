package com.example.schedulemanagerdevelop.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUsernameRequestDto {

    @NotBlank(message = "이름은 필수값입니다.")
    @Max(value = 8, message = "이름은 8자 이내여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

}