package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Exam;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExamService {

    List<ExamDTO> findByStudentId(Long id, Pageable pageable);

    List<ExamDTO> findBySyllabusId(Long id, Pageable pageable);

    List<ExamDTO> findByStudentIdForTeacher(Long id, Long teacherId, Pageable pageable);

    List<ExamDTO> findBySyllabusIdForTeacher(Long id, Long teacherId, Pageable pageable);

    Boolean teacherAuthorizedForStudent(Long studentId, Long teacherId);

    Boolean teacherAuthorizedForSyllabus(Long syllabusId, Long teacherId);

    Exam findOne(Long id);

    List<Exam> findAll();

    Exam save(Exam exam);

    void delete(Long id);
}
