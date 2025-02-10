package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.*;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequestDto dto) {
        Member member = memberRepository.findByIdOrElseThrow(id);
        if (!member.getPassword().equals(dto.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        member.updatePassword(dto.getNewPassword());
    }

    @Transactional
    public void updateUsername(Long id, UpdateUsernameRequestDto dto) {
        Member member = memberRepository.findByIdOrElseThrow(id);
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다.");
        }
        if (!member.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        member.updateUsername(dto.getUsername());
    }

    public void delete(Long id) {
        Member member = memberRepository.findByIdOrElseThrow(id);
        memberRepository.delete(member);
    }

    public MemberResponseDto authenticate(String email, String password) {
        Member member = memberRepository.findByEmailOrElseThrow(email);
        if (!member.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return new MemberResponseDto(member);
    }
}
