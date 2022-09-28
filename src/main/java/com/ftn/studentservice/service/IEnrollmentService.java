package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IEnrollmentService {

    List<Enrollment> findByStudentId(Long id);

    Page<Enrollment> findByStudentId(Long id, PageRequest pageRequest);

    List<Enrollment> getFinishedExams(Long studentId);

    Enrollment findOne(Long id);

    Enrollment findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    List<Enrollment> findAll();

    Enrollment save(Enrollment enrollment);

    void delete(Long id);
}
