package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/student")
public class StudentController {

    private final IStudentService iStudentService;

    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping("/{studentId}/syllabus/{syllabusId}")
    public ResponseEntity<StudentDTO> addStudentToSyllabus(@PathVariable Long studentId, @PathVariable Long syllabusId){
        return new ResponseEntity<>(iStudentService.addStudentToSyllabus(studentId, syllabusId), HttpStatus.OK);
    }
}