package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Student;

import java.util.List;

public interface IStudentService {

    Student findOne(Long id);

    List<Student> findAll();

    Student save(Student student);

    void delete(Long id);
}