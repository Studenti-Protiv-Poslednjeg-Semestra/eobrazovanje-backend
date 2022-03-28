package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {
}