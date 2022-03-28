package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
}