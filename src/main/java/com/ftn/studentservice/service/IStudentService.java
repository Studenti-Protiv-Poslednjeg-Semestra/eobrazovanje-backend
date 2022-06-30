package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.web.dto.StudentDTO;

import java.util.List;

public interface IStudentService {

    Student findOne(Long id);

    List<StudentDTO> findAll();

    List<StudentDTO> findNewStudents();

    StudentDTO addStudentToSyllabus(Long studentId, Long syllabusId);

    StudentDTO enrollmentOnNextSemester(Long studentId);

    Student save(Student student);

    void delete(Long id);
}
