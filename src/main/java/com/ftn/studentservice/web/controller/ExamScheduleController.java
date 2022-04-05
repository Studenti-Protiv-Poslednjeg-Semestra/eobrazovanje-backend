package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.*;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "exam_schedules")
public class ExamScheduleController {

    private final IExamScheduleService examScheduleService;

    public ExamScheduleController(IExamScheduleService examScheduleService) {
        this.examScheduleService = examScheduleService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ExamScheduleDTO> createExamSchedule(@RequestBody ExamScheduleDTO examScheduleDTO){
        ExamScheduleDTO result = examScheduleService.save(examScheduleDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
