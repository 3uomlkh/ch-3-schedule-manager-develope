package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.response.MemberResponseDto;
import com.example.schedulemanagerdevelop.dto.request.UpdatePasswordRequestDto;
import com.example.schedulemanagerdevelop.dto.request.UpdateUsernameRequestDto;
import com.example.schedulemanagerdevelop.service.MemberService;
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

    @PatchMapping("/{id}/username")
    public ResponseEntity<Void> updateUsername(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUsernameRequestDto dto
    ) {
        memberService.updateUsername(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordRequestDto dto
    ) {
        memberService.updatePassword(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
