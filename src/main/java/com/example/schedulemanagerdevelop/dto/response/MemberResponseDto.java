package com.example.schedulemanagerdevelop.dto.response;

import com.example.schedulemanagerdevelop.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final String username;
    private final String email;

    public MemberResponseDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}
