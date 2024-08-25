package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleRequestDto registerScheduleRequestDto) {
        ScheduleResponseDto scheduleRspDto = scheduleService.registerSchedule(registerScheduleRequestDto);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedule(@PathVariable("scheduleId") Long scheduleId) {
        ScheduleResponseDto scheduleRspDto = scheduleService.getSchedule(scheduleId);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable("scheduleId") Long scheduleId, @RequestBody ScheduleRequestDto updateScheduleReqDto) {
        ScheduleResponseDto scheduleRspDto = scheduleService.updateSchedule(scheduleId, updateScheduleReqDto);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }
}
