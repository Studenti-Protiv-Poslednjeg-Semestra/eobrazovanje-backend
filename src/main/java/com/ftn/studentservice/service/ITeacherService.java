package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.web.dto.TeacherDTO;

import java.util.List;

public interface ITeacherService {

    Teacher findOne(Long id);

    List<TeacherDTO> findAll(Integer page);

    TeacherDTO save(TeacherDTO teacher);

    TeacherDTO addProfessorToSubject(Long teacherId, Long subjectId);

    TeacherDTO addAssistantToSubject(Long teacherId, Long subjectId);

    void delete(Long id);
}
