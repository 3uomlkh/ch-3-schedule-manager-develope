package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.CommentResponseDto;
import com.example.schedulemanagerdevelop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commnetService;
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{id}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long id,
            @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("sessionKey");
        CommentResponseDto commentResponseDto = commnetService.save(id, email, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 댓글 조회
    @GetMapping("/schedules/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id) {
        List<CommentResponseDto> commentResponseDtos = commnetService.findAll(id);
        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @RequestBody CommentRequestDto dto
    ) {
        CommentResponseDto commentResponseDto = commentService.update(id, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 삭제
}
