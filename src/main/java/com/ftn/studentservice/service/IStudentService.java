package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IStudentService {

    Student findOne(Long id);

    List<StudentDTO> findAll();

    List<StudentDTO> findAllPageAndSize(PageRequest request);

    List<StudentDTO> findNewStudents();

    StudentDTO addStudentToSyllabus(Long studentId, Long syllabusId);

    StudentDTO enrollmentOnNextSemester(Long studentId);

    Student save(Student student);

    StudentDTO create(StudentDTO student);

    void delete(Long id);
}
