package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllBySyllabus_Id(Long syllabusId);
}