package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.Enrollment;
import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.service.IEnrollmentService;
import com.ftn.studentservice.service.implementation.EnrollmentService;
import com.ftn.studentservice.util.mapper.EnrollmentMapper;
import com.ftn.studentservice.util.mapper.SubjectMapper;
import com.ftn.studentservice.web.dto.EnrollmentDTO;
import com.ftn.studentservice.web.dto.MajorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("enrollments")
public class EnrollmentController {

    private final IEnrollmentService enrollmentService;

    private final EnrollmentMapper enrollmentMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @GetMapping("{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getStudentEntrollments(
            @PathVariable(value = "studentId") Long studentId,
            @RequestParam(value = "page") Optional<Integer> page) {

        List<Enrollment> enrollments = enrollmentService.findByStudentId(s);

//        return new ResponseEntity<>(
//                allMajors
//                        .stream()
//                        .map(majorMapper::toDto)
//                        .toList(),
//                HttpStatus.OK);
    }


}
