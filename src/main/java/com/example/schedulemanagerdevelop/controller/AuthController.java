package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.request.LoginRequestDto;
import com.example.schedulemanagerdevelop.dto.response.MemberResponseDto;
import com.example.schedulemanagerdevelop.dto.request.SignUpRequestDto;
import com.example.schedulemanagerdevelop.dto.response.SignUpResponseDto;
import com.example.schedulemanagerdevelop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        // 회원가입 요청 처리 후 생성된 회원 정보 반환
        SignUpResponseDto signUpResponseDto = memberService.signUp(dto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {
        // 로그인 요청 처리 (이메일 및 비밀번호 검증)
        MemberResponseDto memberResponseDto = memberService.authenticate(dto);

        // 세션 생성 및 이메일로 생성한 세션키 저장
        HttpSession session = request.getSession(true);
        session.setAttribute("sessionKey", memberResponseDto.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // 세션이 존재하면 무효화하여 로그아웃 처리
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
