package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.MemberResponseDto;
import com.example.schedulemanagerdevelop.dto.SignUpRequestDto;
import com.example.schedulemanagerdevelop.dto.SignUpResponseDto;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(SignUpRequestDto dto) {
        Member member = new Member(dto.getUsername(), dto.getEmail(), dto.getPassword());
        Member savedMember = memberRepository.save(member);
        return new SignUpResponseDto(savedMember);
    }

    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(member);
    }
}
