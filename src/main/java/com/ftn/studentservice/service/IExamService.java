package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Exam;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExamService {

    Page<ExamDTO> findByStudentId(Long id, Pageable pageable);

    Page<ExamDTO> findBySyllabusId(Long id, Pageable pageable);

    Page<ExamDTO> findByStudentIdForTeacher(Long id, Long teacherId, Pageable pageable);

    Page<ExamDTO> findBySyllabusIdForTeacher(Long id, Long teacherId, Pageable pageable);

    Boolean teacherAuthorizedForStudent(Long studentId, Long teacherId);

    Boolean teacherAuthorizedForSyllabus(Long syllabusId, Long teacherId);

    ExamDTO findOne(Long id);

    Page<ExamDTO> findAll(Pageable pageable);

    Page<ExamDTO> findAllForTeacher(Long teacherId, Pageable pageable);

    Exam save(Exam exam);

    ExamDTO createExamApplication(Long subjectId, Long studentId);

    ExamDTO update(ExamDTO examDTO);

    void delete(Long id);
}
