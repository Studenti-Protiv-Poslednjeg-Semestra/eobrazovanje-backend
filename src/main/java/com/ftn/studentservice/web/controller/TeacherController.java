package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.ITeacherService;
import com.ftn.studentservice.web.dto.TeacherDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "teacher")
public class TeacherController {

    private final ITeacherService iTeacherService;

    public TeacherController(ITeacherService iTeacherService) {
        this.iTeacherService = iTeacherService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/{teacherId}/professor/subjects/{subjectId}")
    public ResponseEntity<TeacherDTO> addProfessorToSubject(@PathVariable(name = "teacherId") Long teacherId, @PathVariable(name = "subjectId") Long subjectId){
        return new ResponseEntity<>(iTeacherService.addProfessorToSubject(teacherId, subjectId), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/{teacherId}/assistant/subjects/{subjectId}")
    public ResponseEntity<TeacherDTO> addAssistantToSubject(@PathVariable(name = "teacherId") Long teacherId, @PathVariable(name = "subjectId") Long subjectId){
        return new ResponseEntity<>(iTeacherService.addAssistantToSubject(teacherId, subjectId), HttpStatus.OK);
    }
}
