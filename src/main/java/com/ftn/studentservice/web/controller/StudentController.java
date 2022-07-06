package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IEnrollmentService;
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
    private final IEnrollmentService iEnrollmentService;

    public StudentController(IStudentService iStudentService, IEnrollmentService iEnrollmentService) {
        this.iStudentService = iStudentService;
        this.iEnrollmentService = iEnrollmentService;
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
    @PutMapping(value = "/semester-enrollment")
    public ResponseEntity<StudentDTO> enrollmentOnNextSemester(@RequestBody Long studentId) {
        return new ResponseEntity<>(iStudentService.enrollmentOnNextSemester(studentId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public ResponseEntity<List<StudentDTO>> getNewStudents() {
        return new ResponseEntity<>(iStudentService.findNewStudents(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(value = "/{studentId}/finished-exams")
    public ResponseEntity<Integer> getFinishedExams(@PathVariable Long studentId){
        return new ResponseEntity<>(iEnrollmentService.getFinishedExams(studentId).size(), HttpStatus.OK);
    }
}
