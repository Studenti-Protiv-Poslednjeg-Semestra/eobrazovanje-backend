package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "students")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> allStudents = iStudentService.findAll();
        return new ResponseEntity<List<StudentDTO>>(allStudents, HttpStatus.OK);

    @PreAuthorize(value = "hasAnyRole('STUDENT')")
    @PutMapping("/{studentId}/semester")
    public ResponseEntity<StudentDTO> enrollmentOnNextSemester(@PathVariable Long studentId){
        return new ResponseEntity<>(iStudentService.enrollmentOnNextSemester(studentId), HttpStatus.OK);

    }
}
