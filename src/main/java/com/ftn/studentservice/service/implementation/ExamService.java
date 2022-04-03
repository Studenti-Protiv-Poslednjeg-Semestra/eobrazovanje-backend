package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.repository.ExamRepository;
import com.ftn.studentservice.service.IExamService;
import com.ftn.studentservice.util.mapper.ExamMapper;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExamService implements IExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final EnrollmentService enrollmentService;
    private final SyllabusService syllabusService;

    public ExamService(ExamRepository examRepository, ExamMapper examMapper, EnrollmentService enrollmentService, SyllabusService syllabusService) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
        this.enrollmentService = enrollmentService;
        this.syllabusService = syllabusService;
    }


    @Override
    public List<ExamDTO> findByStudentId(Long id, Pageable pageable) {
        List<ExamDTO> exams = examRepository.findByStudentId(id, pageable).stream().map(examMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        return exams.isEmpty() ? null : exams;
    }

    @Override
    public List<ExamDTO> findBySyllabusId(Long id, Pageable pageable) {
        List<ExamDTO> exams = examRepository.findByExamScheduleSubjectSyllabusId(id, pageable).stream().map(examMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        return exams.isEmpty() ? null : exams;
    }

    @Override
    public List<ExamDTO> findByStudentIdForTeacher(Long id, Long teacherId, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findByStudentId(id, pageable);

        List<Exam> exams = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            for (Teacher professor : exam.getExamSchedule().getSubject().getProfessors()) {
                if (!Objects.equals(professor.getId(), teacherId)) {
                    exams.remove(exam);
                    break;
                }
            }
        }

        Page<Exam> examsPageable = new PageImpl<>(exams, pageable,exams.size());

        return examsPageable.stream().map(examMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<ExamDTO> findBySyllabusIdForTeacher(Long id, Long teacherId, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findByExamScheduleSubjectSyllabusId(id, pageable);

        List<Exam> exams = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            for (Teacher professor : exam.getExamSchedule().getSubject().getProfessors()) {
                if (!Objects.equals(professor.getId(), teacherId)) {
                    exams.remove(exam);
                    break;
                }
            }
        }

        Page<Exam> examsPageable = new PageImpl<>(exams, pageable,exams.size());

        return examsPageable.stream().map(examMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Boolean teacherAuthorizedForStudent(Long studentId, Long teacherId){
        List<Enrollment> enrollmentsForStudent = enrollmentService.findByStudentId(studentId);
        enrollmentsForStudent = enrollmentsForStudent == null ?  new ArrayList<>(): enrollmentsForStudent;
        boolean authorized = false;
        for (Enrollment enrollment : enrollmentsForStudent) {
            for (Teacher teacher : enrollment.getSubject().getProfessors()) {
                if (Objects.equals(teacher.getId(), teacherId)) {
                    authorized = true;
                    break;
                }
            }
            if (authorized) {
                break;
            }
        }
        return authorized;
    }

    @Override
    public Boolean teacherAuthorizedForSyllabus(Long syllabusId, Long teacherId){
        Syllabus syllabus = syllabusService.findOne(syllabusId);
        boolean authorized = false;
        for (Subject subject : syllabus.getSubjects()) {
            for (Teacher teacher : subject.getProfessors()) {
                if (Objects.equals(teacher.getId(), teacherId)) {
                    authorized = true;
                    break;
                }
            }
            if (authorized) {
                break;
            }
        }
        return authorized;
    }

    @Override
    public Exam findOne(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}
