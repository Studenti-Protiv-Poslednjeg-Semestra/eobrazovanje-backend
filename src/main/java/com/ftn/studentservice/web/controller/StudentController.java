package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.web.dto.StudentDTO;
import com.ftn.studentservice.web.dto.StudentToSyllabusDTO;
import org.springframework.data.domain.PageRequest;
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
    @PostMapping("/syllabus")
    public ResponseEntity<StudentDTO> addStudentToSyllabus(@RequestBody StudentToSyllabusDTO studentToSyllabusDTO) {
        return new ResponseEntity<>(iStudentService.addStudentToSyllabus(studentToSyllabusDTO.getStudentId(), studentToSyllabusDTO.getSyllabusId()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> allStudents = iStudentService.findAll();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/pagination")
    public ResponseEntity<List<StudentDTO>> getAllStudentsPageAndSize(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        List<StudentDTO> allStudents = iStudentService.findAllPageAndSize(PageRequest.of(page, size));
        return new ResponseEntity<>(allStudents, HttpStatus.OK);

    }

    @PreAuthorize(value = "hasAnyRole('STUDENT')")
    @GetMapping("/{studentId}/semester")
    public ResponseEntity<StudentDTO> enrollmentOnNextSemester(@PathVariable Long studentId) {
        return new ResponseEntity<>(iStudentService.enrollmentOnNextSemester(studentId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public ResponseEntity<List<StudentDTO>> getNewStudents() {
        return new ResponseEntity<>(iStudentService.findNewStudents(), HttpStatus.OK);
    }
}
