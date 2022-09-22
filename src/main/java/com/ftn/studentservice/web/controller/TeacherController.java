package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.ITeacherService;
import com.ftn.studentservice.web.dto.TeacherDTO;
import com.ftn.studentservice.web.dto.TeacherToSubjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "teachers")
public class TeacherController {

    private final ITeacherService iTeacherService;

    public TeacherController(ITeacherService iTeacherService) {
        this.iTeacherService = iTeacherService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getTeachers(@RequestParam(value = "page") Integer page) {
        return new ResponseEntity<>(iTeacherService.findAll(page), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return new ResponseEntity<>(iTeacherService.save(teacherDTO), HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/professor/subjects")
    public ResponseEntity<TeacherDTO> addProfessorToSubject(@RequestBody TeacherToSubjectDTO teacherToSubjectDTO) {
        return new ResponseEntity<>(iTeacherService.addProfessorToSubject(teacherToSubjectDTO.getTeacherId(), teacherToSubjectDTO.getSubjectId()), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/assistant/subjects")
    public ResponseEntity<TeacherDTO> addAssistantToSubject(@RequestBody TeacherToSubjectDTO teacherToSubjectDTO) {
        return new ResponseEntity<>(iTeacherService.addAssistantToSubject(teacherToSubjectDTO.getTeacherId(), teacherToSubjectDTO.getSubjectId()), HttpStatus.OK);
    }
}
