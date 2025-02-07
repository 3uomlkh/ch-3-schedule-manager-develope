package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.SignUpRequestDto;
import com.example.schedulemanagerdevelop.dto.SignUpResponseDto;
import com.example.schedulemanagerdevelop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
        SignUpResponseDto signUpResponseDto =
                memberService.signUp(
                        dto.getUsername(),
                        dto.getEmail(),
                        dto.getPassword()
                );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }
}
