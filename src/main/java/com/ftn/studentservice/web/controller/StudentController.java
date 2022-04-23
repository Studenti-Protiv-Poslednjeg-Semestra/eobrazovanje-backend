package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.service.ISubjectService;
import com.ftn.studentservice.web.dto.StudentDTO;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "students")
public class StudentController {

    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> allStudents = studentService.findAll();
        return new ResponseEntity<List<StudentDTO>>(allStudents, HttpStatus.OK);
    }

}
