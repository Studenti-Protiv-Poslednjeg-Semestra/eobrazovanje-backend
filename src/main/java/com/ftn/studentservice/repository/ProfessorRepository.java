package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}