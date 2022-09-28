package com.ftn.studentservice.web.controller;
import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.service.IMajorService;
import com.ftn.studentservice.util.mapper.MajorMapper;
import com.ftn.studentservice.web.dto.MajorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
