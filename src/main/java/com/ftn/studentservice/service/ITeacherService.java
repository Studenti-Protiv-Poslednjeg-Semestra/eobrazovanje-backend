package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.web.dto.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeacherService {

    Teacher findOne(Long id);

    List<TeacherDTO> findAll(Integer page);

    Page<TeacherDTO> findAllByPage(Pageable pageable);

    TeacherDTO save(TeacherDTO teacher);

    TeacherDTO addProfessorToSubject(Long teacherId, Long subjectId);

    TeacherDTO addAssistantToSubject(Long teacherId, Long subjectId);

    void delete(Long id);
}
