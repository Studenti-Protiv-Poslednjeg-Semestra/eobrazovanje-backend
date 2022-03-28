package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long> {
}