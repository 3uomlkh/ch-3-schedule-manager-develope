package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.config.PasswordEncoder;
import com.example.schedulemanagerdevelop.dto.request.CreateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.request.UpdateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.response.PagedScheduleResponseDto;
import com.example.schedulemanagerdevelop.dto.response.ScheduleResponseDto;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.exception.custom.ScheduleNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.SessionNotFoundException;
import com.example.schedulemanagerdevelop.exception.custom.UnauthorizedActionException;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import com.example.schedulemanagerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ScheduleResponseDto save(CreateScheduleRequestDto dto, Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(SessionNotFoundException::new);

        // 새로운 일정 생성 및 저장
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents());
        schedule.setMember(member);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.of(savedSchedule);
    }

    public List<ScheduleResponseDto> findAll() {
        // 모든 일정 목록 조회 (없으면 빈 리스트 반환)
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::of)
                .toList();
    }

    public Page<PagedScheduleResponseDto> findAllWithPagination(Pageable pageable) {
        // 페이지네이션을 적용하여 일정 목록 조회
        return scheduleRepository.findAll(pageable)
                .map(PagedScheduleResponseDto::of);
    }

    public ScheduleResponseDto findById(Long id) {
        // ID로 일정 조회 (없으면 예외 발생)
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(ScheduleNotFoundException::new);

        return ScheduleResponseDto.of(schedule);
    }

    @Transactional
    public void delete(Long id) {
        // ID로 일정 조회 후 삭제 (없으면 예외 발생)
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(ScheduleNotFoundException::new);

        scheduleRepository.delete(schedule);
    }

    @Transactional
    public void update(Long userId, Long scheduleId, UpdateScheduleRequestDto dto) {
        // ID로 일정 조회 (없으면 예외 발생)
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        // 일정 수정 권한 확인 (로그인한 사용자와 작성자가 일치하는지 검증)
        if (!schedule.getMember().getId().equals(userId)) {
            throw new UnauthorizedActionException();
        }

        // 일정 제목이 있으면 제목 수정
        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            schedule.updateTitle(dto.getTitle());
        }

        // 일정 내용이 있으면 내용 수정
        if (dto.getContents() != null && !dto.getContents().isEmpty()) {
            schedule.updateContents(dto.getContents());
        }
    }
}
