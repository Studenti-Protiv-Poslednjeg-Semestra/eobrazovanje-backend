package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.User;
import com.ftn.studentservice.service.implementation.SubjectService;
import com.ftn.studentservice.util.mapper.SubjectMapperImpl;
import com.ftn.studentservice.web.dto.SubjectCreationDTO;
import com.ftn.studentservice.web.dto.ExamDTO;
import com.ftn.studentservice.web.dto.SubjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
      
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("subjects")
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> allSubjects = subjectService.findAll();
        return new ResponseEntity<List<SubjectDTO>>(allSubjects, HttpStatus.OK);
    }

}
