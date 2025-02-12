package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.config.PasswordEncoder;
import com.example.schedulemanagerdevelop.dto.request.CreateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.response.PagedScheduleResponseDto;
import com.example.schedulemanagerdevelop.dto.response.ScheduleResponseDto;
import com.example.schedulemanagerdevelop.dto.request.UpdateScheduleRequestDto;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import com.example.schedulemanagerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    public ScheduleResponseDto save(CreateScheduleRequestDto dto, String sessionKey) {
        Member member = memberRepository.findByEmailOrElseThrow(sessionKey);

        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents());
        schedule.setMember(member);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public Page<PagedScheduleResponseDto> findAllWithPagination(Pageable pageable) {
        return scheduleRepository.findAll(pageable)
                .map(PagedScheduleResponseDto::toDto);
    }

    public ScheduleResponseDto findbyId(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);
    }

    @Transactional
    public void update(Long id, UpdateScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        // 비밀번호 검증
        boolean isMatch = passwordEncoder.matches(dto.getPassword(), schedule.getMember().getPassword());
        if (!isMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 일정 제목 수정
        if (!(dto.getTitle() == null) && !dto.getTitle().isEmpty()) {
            schedule.updateTitle(dto.getTitle());
        }

        // 일정 내용 수정
        if (dto.getContents() != null && !dto.getContents().isEmpty()) {
            schedule.updateContents(dto.getContents());
        }
    }
}
