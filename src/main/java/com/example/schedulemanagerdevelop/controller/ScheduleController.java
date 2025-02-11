package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagerdevelop.dto.UpdateScheduleRequestDto;
import com.example.schedulemanagerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody CreateScheduleRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(dto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findbyId(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateScheduleRequestDto dto
    ) {
       scheduleService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
