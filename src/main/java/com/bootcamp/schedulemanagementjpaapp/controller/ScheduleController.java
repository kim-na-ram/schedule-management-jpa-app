package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleFindResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleRequestDto registerScheduleRequestDto, HttpServletRequest request) {
        ScheduleResponseDto scheduleRspDto = scheduleService.registerSchedule((String) request.getAttribute("email"), registerScheduleRequestDto);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedule(@PathVariable("scheduleId") Long scheduleId) {
        ScheduleResponseDto scheduleRspDto = scheduleService.getSchedule(scheduleId);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> getAllSchedules(@PageableDefault(sort = "updateDate", direction = Sort.Direction.DESC) final Pageable pageable) {
        List<ScheduleFindResponseDto> schedulesResponseDto = scheduleService.getScheduleList(pageable);
        return new ResponseEntity<>(schedulesResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable("scheduleId") Long scheduleId, @RequestBody ScheduleRequestDto updateScheduleReqDto, HttpServletRequest request) {
        ScheduleResponseDto scheduleRspDto = scheduleService.updateSchedule(scheduleId, (String) request.getAttribute("email"), updateScheduleReqDto);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("scheduleId") Long scheduleId, HttpServletRequest request) {
        scheduleService.deleteSchedule(scheduleId, (String) request.getAttribute("email"));
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
