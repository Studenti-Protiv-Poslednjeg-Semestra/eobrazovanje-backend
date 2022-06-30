package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.service.ISubjectService;
import com.ftn.studentservice.util.mapper.SubjectMapperImpl;
import com.ftn.studentservice.web.dto.SubjectCreationDTO;
import com.ftn.studentservice.web.dto.SubjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("subjects")
public class SubjectController {

    private final ISubjectService subjectService;
    private final SubjectMapperImpl subjectMapper;
    private static final Integer SIZE = 10;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody SubjectCreationDTO dto) {
        Subject newSubject = subjectMapper.toEntity(dto);

        subjectService.createSubject(newSubject);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(@RequestParam(value = "page") Optional<Integer> page) {
        if(page.isPresent()){
            return new ResponseEntity<>(subjectService.findAll(PageRequest.of(page.get(), SIZE)), HttpStatus.OK);
        }
        List<SubjectDTO> allSubjects = subjectService.findAll();
        return new ResponseEntity<List<SubjectDTO>>(allSubjects, HttpStatus.OK);
    }

}
