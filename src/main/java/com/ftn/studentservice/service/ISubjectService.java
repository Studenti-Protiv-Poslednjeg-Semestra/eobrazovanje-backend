package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Subject;

import java.util.List;

public interface ISubjectService {

    Subject findOne(Long id);

    List<Subject> findAll();

    Subject save(Subject subject);

    void delete(Long id);
}
