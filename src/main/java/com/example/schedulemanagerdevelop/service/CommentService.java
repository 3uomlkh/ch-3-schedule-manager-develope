package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.request.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.response.CommentResponseDto;
import com.example.schedulemanagerdevelop.entity.Comment;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.exception.custom.CommentNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.EmailNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.ScheduleNotFoundException;
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
    public CommentResponseDto save(Long scheduleId, String email, CommentRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
        Comment comment = new Comment(dto.getContents(), member, schedule);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    public List<CommentResponseDto> findAll(Long id) {
        List<Comment> comments = commentRepository.findByScheduleId(id);

        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto update(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);

        comment.updateContents(dto.getContents());

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);
    }
}
