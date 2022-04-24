package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final SubjectRepository subjectRepository;

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

    @Override
    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }
}
