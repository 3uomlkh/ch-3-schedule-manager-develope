package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.consts.Const;
import com.example.schedulemanagerdevelop.dto.request.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.response.CommentResponseDto;
import com.example.schedulemanagerdevelop.service.CommentService;
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
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long scheduleId,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        CommentResponseDto commentResponseDto = commentService.save(userId, scheduleId, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 특정 일정의 모든 댓글 조회
    @GetMapping("/schedules/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id) {
        List<CommentResponseDto> commentResponseDtos = commentService.findAll(id);
        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        CommentResponseDto commentResponseDto = commentService.update(userId, commentId, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> delete(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long commentId
    ) {
        commentService.delete(commentId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
