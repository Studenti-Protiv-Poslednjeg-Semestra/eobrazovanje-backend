package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISubjectService {

    Subject findOne(Long id);

    Page<Subject> findAll(Pageable pageable);

    Subject save(Subject subject);

    void delete(Long id);

    void createSubject(Subject subject);
}
