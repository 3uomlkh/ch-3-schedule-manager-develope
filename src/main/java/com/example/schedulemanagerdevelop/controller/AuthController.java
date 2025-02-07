package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.SignUpRequestDto;
import com.example.schedulemanagerdevelop.dto.SignUpResponseDto;
import com.example.schedulemanagerdevelop.service.MemberService;
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
}
