package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.service.ISubjectService;
import com.ftn.studentservice.web.dto.ExamDTO;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "subjects")
public class SubjectController {

    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> allSubjects = subjectService.findAll();
        return new ResponseEntity<List<SubjectDTO>>(allSubjects, HttpStatus.OK);
    }

}
