package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.request.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.response.CommentResponseDto;
import com.example.schedulemanagerdevelop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{id}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {
        // 현재 로그인 된 유저의 세션키 가져오기
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        CommentResponseDto commentResponseDto = commentService.save(id, sessionKey, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 특정 일정의 모든 댓글 조회
    @GetMapping("/schedules/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id) {
        List<CommentResponseDto> commentResponseDtos = commentService.findAll(id);
        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {
        // 현재 로그인 된 유저의 세션키 가져오기
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        CommentResponseDto commentResponseDto = commentService.update(id, dto, sessionKey);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        // 현재 로그인 된 유저의 세션키 가져오기
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        commentService.delete(id, sessionKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
