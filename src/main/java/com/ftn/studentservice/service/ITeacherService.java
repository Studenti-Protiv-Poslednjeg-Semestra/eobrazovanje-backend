package com.ftn.studentservice.service;
import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.web.dto.SubjectDTO;
import com.ftn.studentservice.web.dto.TeacherDTO;

import java.util.List;

public interface ITeacherService {

    Teacher findOne(Long id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    TeacherDTO addProfessorToSubject(Long teacherId, Long subjectId);

    TeacherDTO addAssistantToSubject(Long teacherId, Long subjectId);

    void delete(Long id);
}
