package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.SignUpResponseDto;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String email, String password) {
        Member member = new Member(username, email, password);
        Member savedMember = memberRepository.save(member);
        return new SignUpResponseDto(savedMember);
    }
}
