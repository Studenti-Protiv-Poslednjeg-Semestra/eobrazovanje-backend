package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    boolean existsByName(String majorName);
}