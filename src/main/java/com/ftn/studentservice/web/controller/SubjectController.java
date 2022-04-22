package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.implementation.SubjectService;
import com.ftn.studentservice.web.dto.SubjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> createSubject(@RequestBody SubjectDTO dto) {
        try {
            subjectService.createSubject(dto);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getCause(), HttpStatus.BAD_REQUEST);
        }
    }

}
