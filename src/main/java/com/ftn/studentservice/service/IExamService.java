package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Exam;

import java.util.List;

public interface IExamService {

    List<Exam> findByStudentId(Long id);

    Exam findOne(Long id);

    List<Exam> findAll();

    Exam save(Exam exam);

    void delete(Long id);
}
