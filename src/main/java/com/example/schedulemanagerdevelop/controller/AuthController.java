package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.LoginRequestDto;
import com.example.schedulemanagerdevelop.dto.MemberResponseDto;
import com.example.schedulemanagerdevelop.dto.SignUpRequestDto;
import com.example.schedulemanagerdevelop.dto.SignUpResponseDto;
import com.example.schedulemanagerdevelop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(dto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {
        MemberResponseDto memberResponseDto = memberService.authenticate(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession(true);
        session.setAttribute("sessionKey", memberResponseDto.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
