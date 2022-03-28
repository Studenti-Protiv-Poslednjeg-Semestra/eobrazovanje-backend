package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}