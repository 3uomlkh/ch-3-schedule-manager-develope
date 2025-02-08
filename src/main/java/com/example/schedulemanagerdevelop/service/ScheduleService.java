package com.example.schedulemanagerdevelop.service;

import com.example.schedulemanagerdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagerdevelop.entity.Member;
import com.example.schedulemanagerdevelop.entity.Schedule;
import com.example.schedulemanagerdevelop.repository.MemberRepository;
import com.example.schedulemanagerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(CreateScheduleRequestDto dto) {
        Member member = memberRepository.findMemberByUsernameOrElseThrow(dto.getUsername());

        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents());
        schedule.setMember(member);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }
}
