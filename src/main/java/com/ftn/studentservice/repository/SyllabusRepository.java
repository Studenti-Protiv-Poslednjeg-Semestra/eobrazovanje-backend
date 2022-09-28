package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Syllabus;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
    //Syllabus findByMajor_IdAndYearOfCreationBetween(Long majorId, LocalDate startDate, LocalDate endDate);
    boolean existsByMajor_IdAndYearOfCreationBetween(Long majorId, LocalDate startDate, LocalDate endDate);
    List<Syllabus> getSyllabusByMajorId(Long majorId);
}