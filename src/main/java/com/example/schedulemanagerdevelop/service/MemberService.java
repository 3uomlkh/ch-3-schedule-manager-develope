package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.config.PasswordEncoder;
import com.example.schedulemanagerdevelop.dto.request.LoginRequestDto;
import com.example.schedulemanagerdevelop.dto.request.SignUpRequestDto;
import com.example.schedulemanagerdevelop.dto.request.UpdatePasswordRequestDto;
import com.example.schedulemanagerdevelop.dto.request.UpdateUsernameRequestDto;
import com.example.schedulemanagerdevelop.dto.response.MemberResponseDto;
import com.example.schedulemanagerdevelop.dto.response.SignUpResponseDto;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.exception.custom.EmailNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.IncorrectPasswordException;
import com.example.schedulemanagerdevelop.exception.custom.UserNotFoundException;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto dto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = new Member(dto.getUsername(), dto.getEmail(), encodedPassword);
        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember);
    }

    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return new MemberResponseDto(member);
    }

    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequestDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        boolean isMatch = passwordEncoder.matches(dto.getOldPassword(), member.getPassword());

        if (!isMatch) {
            throw new IncorrectPasswordException();
        }

        member.updatePassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    @Transactional
    public void updateUsername(Long id, UpdateUsernameRequestDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        boolean isMatch = passwordEncoder.matches(dto.getPassword(), member.getPassword());

        if (!isMatch) {
            throw new IncorrectPasswordException();
        }

        member.updateUsername(dto.getUsername());
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);

        memberRepository.delete(member);
    }

    public MemberResponseDto authenticate(LoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(EmailNotFoundException::new);
        boolean isMatch = passwordEncoder.matches(dto.getPassword(), member.getPassword());

        if (!isMatch) {
            throw new IncorrectPasswordException();
        }

        return new MemberResponseDto(member);
    }
}
