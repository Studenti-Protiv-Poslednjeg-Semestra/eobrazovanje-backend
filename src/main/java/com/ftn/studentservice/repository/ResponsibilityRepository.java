package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Responsibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsibilityRepository extends JpaRepository<Responsibility, Long> {
}