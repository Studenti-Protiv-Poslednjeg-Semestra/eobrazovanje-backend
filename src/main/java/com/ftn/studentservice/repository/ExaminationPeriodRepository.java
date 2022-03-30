package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.ExaminationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationPeriodRepository extends JpaRepository<ExaminationPeriod, Long> {
}