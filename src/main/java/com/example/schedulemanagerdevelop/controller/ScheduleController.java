package com.example.schedulemanagerdevelop.controller;

import com.example.schedulemanagerdevelop.dto.request.CreateScheduleRequestDto;
import com.example.schedulemanagerdevelop.dto.response.PagedScheduleResponseDto;
import com.example.schedulemanagerdevelop.dto.response.ScheduleResponseDto;
import com.example.schedulemanagerdevelop.dto.request.UpdateScheduleRequestDto;
import com.example.schedulemanagerdevelop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<ScheduleResponseDto> save(
            @Valid @RequestBody CreateScheduleRequestDto dto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(dto, sessionKey);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<PagedScheduleResponseDto>> findAllWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<PagedScheduleResponseDto> pagedScheduleResponseDto = scheduleService.findAllWithPagination(pageable);
        return new ResponseEntity<>(pagedScheduleResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateScheduleRequestDto dto,
            HttpServletRequest request
    ) {
        // 현재 로그인 된 유저의 세션키 가져오기
        HttpSession session = request.getSession(false);
        String sessionKey = (String) session.getAttribute("sessionKey");

        scheduleService.update(id, dto, sessionKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
