package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.response.MemberResponseDto;
import com.example.schedulemanagerdevelop.dto.request.UpdatePasswordRequestDto;
import com.example.schedulemanagerdevelop.dto.request.UpdateUsernameRequestDto;
import com.example.schedulemanagerdevelop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/username")
    public ResponseEntity<Void> updateUsername(
            @Valid @RequestBody UpdateUsernameRequestDto dto,
            HttpServletRequest request
    ) {
        // 현재 로그인 된 유저의 세션키 가져오기
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        memberService.updateUsername(dto, sessionKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody UpdatePasswordRequestDto dto,
            HttpServletRequest request
    ) {
        // 현재 로그인 된 유저의 세션키 가져오기
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        memberService.updatePassword(dto, sessionKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
