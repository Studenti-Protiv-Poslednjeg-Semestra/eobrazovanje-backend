package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Professor;

import java.util.List;

public interface IProfessorService {

    Professor findOne(Long id);

    List<Professor> findAll();

    Professor save(Professor professor);

    void delete(Long id);
}
