package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Page<Exam> findAll(Pageable pageable);

    List<Exam> findByStudentId(Long id);

    Page<Exam> findByStudentId(Long id, Pageable pageable);

    Page<Exam> findByExamScheduleSubjectSyllabusId(Long id, Pageable pageable);

}