package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.service.implementation.SubjectService;
import com.ftn.studentservice.util.mapper.SubjectMapperImpl;
import com.ftn.studentservice.web.dto.SubjectCreationDTO;
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
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapperImpl subjectMapper;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody SubjectCreationDTO dto) {
        Subject newSubject = subjectMapper.toEntity(dto);

        subjectService.createSubject(newSubject);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
