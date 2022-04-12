package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {

    List<Enrollment> findByStudentId(Long id);

    Enrollment findOne(Long id);

    List<Enrollment> findAll();

    Enrollment save(Enrollment enrollment);

    void delete(Long id);
}
