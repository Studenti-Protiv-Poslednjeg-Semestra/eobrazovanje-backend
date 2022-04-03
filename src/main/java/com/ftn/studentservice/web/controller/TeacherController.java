package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.ITeacherService;
import com.ftn.studentservice.web.dto.SubjectDTO;
import com.ftn.studentservice.web.dto.TeacherDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/teacher")
public class TeacherController {

    private final ITeacherService iTeacherService;

    public TeacherController(ITeacherService iTeacherService) {
        this.iTeacherService = iTeacherService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/{teacherId}/professor/subjects")
    public ResponseEntity<TeacherDTO> addProfessorToSubject(@PathVariable Long teacherId, @RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<>(iTeacherService.addProfessorToSubject(teacherId, subjectDTO), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/{teacherId}/assistant/subjects")
    public ResponseEntity<TeacherDTO> addAssistentToSubject(@PathVariable Long teacherId, @RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<>(iTeacherService.addAssistantToSubject(teacherId, subjectDTO), HttpStatus.OK);
    }
}
