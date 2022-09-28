package com.ftn.studentservice.web.controller;
import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.service.IMajorService;
import com.ftn.studentservice.util.mapper.MajorMapper;
import com.ftn.studentservice.web.dto.MajorCreationDTO;
import com.ftn.studentservice.web.dto.MajorDTO;
import com.ftn.studentservice.web.dto.subject.SubjectCreationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("majors")
public class MajorController {

    private final IMajorService majorService;
    private final MajorMapper majorMapper;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<MajorDTO>> getAllMajors() {
        List<Major> allMajors = majorService.findAll();

        return new ResponseEntity<>(
                allMajors
                        .stream()
                        .map(majorMapper::toDto)
                        .toList(),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createMajor(@Valid @RequestBody MajorCreationDTO majorCreationDTO) {
        Major newMajor = majorMapper.toEntity(majorCreationDTO);
        majorService.save(newMajor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
