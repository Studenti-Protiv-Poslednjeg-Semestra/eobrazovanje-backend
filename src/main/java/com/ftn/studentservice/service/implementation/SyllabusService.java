package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.repository.SyllabusRepository;
import com.ftn.studentservice.service.ISyllabusService;
import com.ftn.studentservice.util.mapper.SyllabusMapper;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyllabusService implements ISyllabusService {

    private final SyllabusRepository  syllabusRepository;
    private final SyllabusMapper syllabusMapper;

    public SyllabusService(SyllabusRepository syllabusRepository, SyllabusMapper syllabusMapper) {
        this.syllabusRepository = syllabusRepository;
        this.syllabusMapper = syllabusMapper;
    }

    @Override
    public Syllabus findOne(Long id) {
        return syllabusRepository.findById(id).orElse(null);
    }

    @Override
    public List<SyllabusDTO> findAll() {
        return syllabusRepository.findAll().stream().map(syllabusMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Syllabus save(Syllabus syllabus) {
        return syllabusRepository.save(syllabus);
    }

    @Override
    public void delete(Long id) {
        syllabusRepository.deleteById(id);
    }
}
