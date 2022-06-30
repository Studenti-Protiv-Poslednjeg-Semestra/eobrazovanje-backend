package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISubjectService {

    Subject findOne(Long id);

    List<SubjectDTO> findAll(Pageable pageable);

    List<SubjectDTO> findAll();

    Subject save(Subject subject);

    void delete(Long id);

    void createSubject(Subject subject);
}
