package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.model.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllBySyllabus(Syllabus syllabus);

}