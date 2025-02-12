package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.CommentRequestDto;
import com.example.schedulemanagerdevelop.dto.CommentResponseDto;
import com.example.schedulemanagerdevelop.entity.Comment;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.repository.CommentRepository;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import com.example.schedulemanagerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
