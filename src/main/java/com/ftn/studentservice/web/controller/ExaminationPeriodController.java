package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.ExaminationPeriod;
import com.ftn.studentservice.service.IExaminationPeriodService;
import com.ftn.studentservice.web.dto.ExaminationPeriodDTO;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "examination_periods")
public class ExaminationPeriodController {

    private final IExaminationPeriodService examinationPeriodService;

    public ExaminationPeriodController(IExaminationPeriodService examinationPeriodService) {
        this.examinationPeriodService = examinationPeriodService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ExaminationPeriodDTO>> getAllExamPeriods() {
        List<ExaminationPeriodDTO> allExamPeriods = examinationPeriodService.findAll();
        return new ResponseEntity<List<ExaminationPeriodDTO>>(allExamPeriods, HttpStatus.OK);
    }

}
