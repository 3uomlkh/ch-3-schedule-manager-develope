package com.example.schedulemanagerdevelop.dto;

import com.example.schedulemanagerdevelop.entity.Member;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public SignUpResponseDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}
