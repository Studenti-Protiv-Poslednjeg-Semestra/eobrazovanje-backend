package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {

    List<Enrollment> findByStudentId(Long id);

    List<Enrollment> getFinishedExams(Long studentId);

    Enrollment findOne(Long id);

    Enrollment findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    List<Enrollment> findAll();

    Enrollment save(Enrollment enrollment);

    void delete(Long id);
}
