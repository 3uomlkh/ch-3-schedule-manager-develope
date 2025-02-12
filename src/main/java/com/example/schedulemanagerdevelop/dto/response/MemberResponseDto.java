package com.example.schedulemanagerdevelop.dto.response;

import com.example.schedulemanagerdevelop.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final String username;
    private final String email;

    public MemberResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getUsername(),
                member.getEmail()
        );
    }
}
