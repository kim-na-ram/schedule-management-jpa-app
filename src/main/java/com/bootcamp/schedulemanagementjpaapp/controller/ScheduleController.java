package com.bootcamp.schedulemanagementjpaapp.controller;

import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleRegisterRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.request.ScheduleUpdateRequestDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleFindResponseDto;
import com.bootcamp.schedulemanagementjpaapp.dto.response.ScheduleResponseDto;
import com.bootcamp.schedulemanagementjpaapp.service.schedule.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<?> registerSchedule(
            @RequestBody ScheduleRegisterRequestDto scheduleRegisterRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        ScheduleResponseDto scheduleRspDto =
                scheduleService.registerSchedule(String.valueOf(httpServletRequest.getAttribute(USER_EMAIL)), scheduleRegisterRequestDto);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedule(@PathVariable("scheduleId") long scheduleId) {
        ScheduleResponseDto scheduleRspDto = scheduleService.getSchedule(scheduleId);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> getAllSchedules(
            @PageableDefault(sort = "updateDate", direction = Sort.Direction.DESC) final Pageable pageable
    ) {
        List<ScheduleFindResponseDto> schedulesResponseDto = scheduleService.getScheduleList(pageable);
        return new ResponseEntity<>(schedulesResponseDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable("scheduleId") long scheduleId,
            @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto
    ) {
        ScheduleResponseDto scheduleRspDto =
                scheduleService.updateSchedule(scheduleId, scheduleUpdateRequestDto);
        return new ResponseEntity<>(scheduleRspDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("scheduleId") long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(SUCCESS.getResultMessage(), SUCCESS.getHttpStatus());
    }
}
