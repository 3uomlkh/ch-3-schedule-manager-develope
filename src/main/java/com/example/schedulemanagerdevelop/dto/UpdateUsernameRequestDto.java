package com.example.schedulemanagerdevelop.dto;

import lombok.Getter;

@Getter
public class UpdateUsernameRequestDto {
    private final String username;
    private final String password;

    public UpdateUsernameRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}