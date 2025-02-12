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
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commnetService;

    // 댓글 생성
    @PostMapping("/{id}/comments")
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
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id) {
        List<CommentResponseDto> commentResponseDtos = commnetService.findAll(id);
        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    // 댓글 수정

    // 댓글 삭제
}
