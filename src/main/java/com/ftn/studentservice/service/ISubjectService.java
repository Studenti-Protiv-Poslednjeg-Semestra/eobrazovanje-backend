package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISubjectService {

    Subject findOne(Long id);

    Page<Subject> findAll(Pageable pageable);

    List<SubjectDTO> findAll();

    Subject save(Subject subject);

    void delete(Long id);
}
