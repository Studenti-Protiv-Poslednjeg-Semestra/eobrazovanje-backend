package com.ftn.studentservice.service;
import com.ftn.studentservice.model.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher findOne(Long id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    void delete(Long id);
}
