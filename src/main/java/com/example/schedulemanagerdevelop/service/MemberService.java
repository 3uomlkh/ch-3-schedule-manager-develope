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
import com.example.schedulemanagerdevelop.exception.custom.SessionNotFoundException;
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

        // 새로운 유저 생성 및 저장
        Member member = new Member(dto.getUsername(), dto.getEmail(), encodedPassword);
        Member savedMember = memberRepository.save(member);

        return SignUpResponseDto.of(savedMember);
    }

    public MemberResponseDto findById(Long id) {
        // ID로 회원 조회 (없으면 예외 발생)
        Member member = memberRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return MemberResponseDto.of(member);
    }

    @Transactional
    public void updatePassword(UpdatePasswordRequestDto dto, String session) {
        Member member = memberRepository.findByEmail(session)
                .orElseThrow(SessionNotFoundException::new);

        // 기존 비밀번호 일치 여부 확인
        boolean isMatch = passwordEncoder.matches(dto.getOldPassword(), member.getPassword());
        if (!isMatch) {
            throw new IncorrectPasswordException();
        }

        // 새 비밀번호 암호화 후 업데이트
        member.updatePassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    @Transactional
    public void updateUsername(UpdateUsernameRequestDto dto, String session) {
        Member member = memberRepository.findByEmail(session)
                .orElseThrow(SessionNotFoundException::new);

        member.updateUsername(dto.getUsername());
    }

    @Transactional
    public void delete(Long id) {
        // ID로 회원 조회 후 삭제 (없으면 예외 발생)
        Member member = memberRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);

        memberRepository.delete(member);
    }

    public MemberResponseDto authenticate(LoginRequestDto dto) {
        // 이메일로 회원 조회 (없으면 예외 발생)
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        // 비밀번호 검증
        boolean isMatch = passwordEncoder.matches(dto.getPassword(), member.getPassword());
        if (!isMatch) {
            throw new IncorrectPasswordException();
        }

        return MemberResponseDto.of(member);
    }

}
