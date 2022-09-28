package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long id);

    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);

    Enrollment findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    List<Enrollment> findEnrollmentsByStudent_IdAndGradeGreaterThan(Long studentId, Integer grade);
}