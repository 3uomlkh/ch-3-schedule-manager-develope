package com.example.schedulemanagerdevelop.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이름은 필수값입니다.")
    @Size(min = 1, max = 8, message = "이름은 8자 이내여야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Size(min = 8, max = 12, message = "비밀번호는 8자 이상, 12자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\W_]).+$", message = "비밀번호는 영문, 숫자, 특수문자를 적어도 1개 이상 포함해야 합니다.")
    private String password;

}
