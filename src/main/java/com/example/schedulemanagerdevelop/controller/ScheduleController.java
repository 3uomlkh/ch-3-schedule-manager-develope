package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagerdevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(dto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }
}
