package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.repository.SyllabusRepository;
import com.ftn.studentservice.service.ISyllabusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyllabusService implements ISyllabusService {

    private final SyllabusRepository  syllabusRepository;

    public SyllabusService(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    @Override
    public Syllabus findOne(Long id) {
        return syllabusRepository.findById(id).orElse(null);
    }

    @Override
    public List<Syllabus> findAll() {
        return syllabusRepository.findAll();
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
