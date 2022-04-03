package com.ftn.studentservice.service;
import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.web.dto.SubjectDTO;
import com.ftn.studentservice.web.dto.TeacherDTO;

import java.util.List;

public interface ITeacherService {

    Teacher findOne(Long id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    TeacherDTO addProfessorToSubject(Long teacherId, SubjectDTO subjectDTO);

    TeacherDTO addAssistantToSubject(Long teacherId, SubjectDTO subjectDTO);

    void delete(Long id);
}
