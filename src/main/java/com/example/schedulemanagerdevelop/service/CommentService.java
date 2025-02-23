package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.request.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.response.CommentResponseDto;
import com.example.schedulemanagerdevelop.entity.Comment;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.exception.custom.CommentNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.ScheduleNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.SessionNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.UnauthorizedActionException;
import com.example.schedulemanagerdevelop.repository.CommentRepository;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import com.example.schedulemanagerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommentResponseDto save(Long userId, Long scheduleId, CommentRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);
        Member member = memberRepository.findById(userId)
                .orElseThrow(SessionNotFoundException::new);
        Comment comment = new Comment(dto.getContents(), member, schedule);

        // 새로운 댓글 생성 및 저장
        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.of(savedComment);
    }

    public List<CommentResponseDto> findAll(Long id) {
        List<Comment> comments = commentRepository.findByScheduleId(id);

        // 특정 일정의 모든 댓글 조회(없으면 빈 리스트 반환)
        return comments.stream()
                .map(CommentResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto update(Long userId, Long commentId, CommentRequestDto dto) {
        // ID로 댓글 조회 (없으면 예외 발생)
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        // 같은 유저인지 세션키로 확인 후 수정
        if (!comment.getMember().getId().equals(userId)) {
            throw new UnauthorizedActionException();
        }

        comment.updateContents(dto.getContents());

        return CommentResponseDto.of(comment);
    }

    @Transactional
    public void delete(Long userId, Long commentId) {
        // ID로 일정 조회 (없으면 예외 발생)
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        // 같은 유저인지 세션키로 확인 후 삭제
        if (!comment.getMember().getId().equals(userId)) {
            throw new UnauthorizedActionException();
        }

        commentRepository.delete(comment);
    }
}
