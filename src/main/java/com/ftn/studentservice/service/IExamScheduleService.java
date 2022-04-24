package com.ftn.studentservice.service;

import com.ftn.studentservice.model.ExamSchedule;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;

import java.util.List;

public interface IExamScheduleService {

    ExamSchedule findOne(Long id);

    List<ExamScheduleDTO> findAll();

    Boolean teacherAuthorizedForSubject(Long teacherId, Long subjectId);
    List<ExamScheduleDTO> findBySubjectId(Long subjectId);

    List<ExamScheduleDTO> findAllForStudent(Long studentId);

    ExamScheduleDTO save(ExamScheduleDTO examScheduleDTO);

    void delete(Long id);
}
