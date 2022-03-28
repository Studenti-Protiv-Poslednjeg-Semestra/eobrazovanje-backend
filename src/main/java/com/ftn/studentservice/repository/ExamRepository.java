package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}