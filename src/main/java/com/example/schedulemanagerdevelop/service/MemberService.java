package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(SignUpRequestDto dto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = new Member(dto.getUsername(), dto.getEmail(), encodedPassword);
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
        boolean isMatch = passwordEncoder.matches(dto.getOldPassword(), member.getPassword());

        if (!isMatch) {
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

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), member.getPassword());
        if (!isMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        member.updateUsername(dto.getUsername());
    }

    public void delete(Long id) {
        Member member = memberRepository.findByIdOrElseThrow(id);
        memberRepository.delete(member);
    }

    public MemberResponseDto authenticate(LoginRequestDto dto) {
        Member member = memberRepository.findByEmailOrElseThrow(dto.getEmail());

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), member.getPassword());
        if (!isMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return new MemberResponseDto(member);
    }
}
