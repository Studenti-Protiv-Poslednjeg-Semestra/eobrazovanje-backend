package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.service.ISyllabusService;
import com.ftn.studentservice.util.mapper.SyllabusMapper;
import com.ftn.studentservice.web.dto.SyllabusCreationDTO;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "syllabi")
public class SyllabusController {

    private final ISyllabusService syllabusService;
    private final SyllabusMapper syllabusMapper;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SyllabusDTO>> getAllSyllabi() {
        return new ResponseEntity<>(syllabusService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/major/{majorId}")
    public ResponseEntity<List<SyllabusDTO>> getSyllabiForMajor(@PathVariable Long majorId) {
        List<Syllabus> syllabi = syllabusService.findSyllabiByMajorId(majorId);

        return new ResponseEntity<>(syllabi.stream().map(syllabusMapper::toDto).toList(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/get-remaining-semester-ects/syllabus/{syllabusId}/semester/{semesterNumber}")
    public ResponseEntity<Integer> getRemainingSemesterECTS(@PathVariable Long syllabusId, @PathVariable int semesterNumber) {
        return new ResponseEntity<>(syllabusService.getRemainingSemesterECTS(syllabusId,
                semesterNumber), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createSyllabus(@Valid @RequestBody SyllabusCreationDTO syllabusCreationDTO) {
        Syllabus newSyllabus = syllabusMapper.toEntity(syllabusCreationDTO);
        syllabusService.save(newSyllabus);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{syllabusId}")
    public ResponseEntity<?> deleteSyllabus(@PathVariable Long syllabusId) {

        syllabusService.delete(syllabusId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
