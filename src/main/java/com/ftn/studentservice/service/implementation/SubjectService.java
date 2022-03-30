package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.service.ISubjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject findOne(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Subject> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}
