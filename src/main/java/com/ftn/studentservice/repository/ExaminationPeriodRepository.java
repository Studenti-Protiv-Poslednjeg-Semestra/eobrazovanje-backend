package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.ExaminationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationPeriodRepository extends JpaRepository<ExaminationPeriod, Long> {
}