package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.service.ISubjectService;
import com.ftn.studentservice.util.mapper.SubjectMapper;
import com.ftn.studentservice.web.dto.SubjectDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService implements ISubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }
    @Override
    public Subject findOne(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<SubjectDTO> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable).stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }
}
