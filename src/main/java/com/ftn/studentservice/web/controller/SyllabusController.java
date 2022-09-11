package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.ISyllabusService;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "syllabi")
public class SyllabusController {

    private final ISyllabusService iSyllabusService;

    public SyllabusController(ISyllabusService iSyllabusService) {
        this.iSyllabusService = iSyllabusService;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SyllabusDTO>> getAllSyllabi() {
        return new ResponseEntity<>(iSyllabusService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/get-remaining-semester-ects/syllabus/{syllabusId}/semester/{semesterNumber}")
    public ResponseEntity<Integer> getRemainingSemesterECTS(@PathVariable Long syllabusId, @PathVariable int semesterNumber) {
        return new ResponseEntity<>(iSyllabusService.getRemainingSemesterECTS(syllabusId,
                semesterNumber), HttpStatus.OK);
    }
}
