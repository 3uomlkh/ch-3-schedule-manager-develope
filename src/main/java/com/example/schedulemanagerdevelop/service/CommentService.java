package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.CommentResponseDto;
import com.example.schedulemanagerdevelop.entity.Comment;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.repository.CommentRepository;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import com.example.schedulemanagerdevelop.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public CommentResponseDto save(Long scheduleId, String email, CommentRequestDto dto) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        Member member = memberRepository.findByEmailOrElseThrow(email);

        Comment comment = new Comment(dto.getContents(), member, schedule);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getContents(),
                member.getUsername(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    public List<CommentResponseDto> findAll(Long id) {
        List<Comment> comments = commentRepository.findByScheduleId(id);
        if (comments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No comments found for scheduleId = " + id);
        }
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto update(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. id = " + id));
        comment.updateContents(dto.getContents());
        return new CommentResponseDto(comment);
    }
}
