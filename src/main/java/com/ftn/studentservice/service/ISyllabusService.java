package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Syllabus;

import java.util.List;

public interface ISyllabusService {

    Syllabus findOne(Long id);

    List<Syllabus> findAll();

    Syllabus save(Syllabus syllabus);

    void delete(Long id);
}
