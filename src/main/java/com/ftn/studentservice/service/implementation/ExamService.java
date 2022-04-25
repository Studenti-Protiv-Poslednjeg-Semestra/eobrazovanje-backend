package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.repository.ExamRepository;
import com.ftn.studentservice.service.IExamService;
import com.ftn.studentservice.util.mapper.ExamMapper;
import com.ftn.studentservice.util.mapper.ExamScheduleMapper;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExamService implements IExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final ExamScheduleMapper examScheduleMapper;
    private final EnrollmentService enrollmentService;
    private final SyllabusService syllabusService;
    private final StudentService studentService;
    private final ExamScheduleService examScheduleService;
    private final double examRegistrationFee = 200;

    public ExamService(ExamRepository examRepository, ExamMapper examMapper, ExamScheduleMapper examScheduleMapper,
                       EnrollmentService enrollmentService, SyllabusService syllabusService, StudentService studentService,
                       ExamScheduleService examScheduleService) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
        this.examScheduleMapper = examScheduleMapper;
        this.enrollmentService = enrollmentService;
        this.syllabusService = syllabusService;
        this.studentService = studentService;
        this.examScheduleService = examScheduleService;
    }

    @Override
    public Page<ExamDTO> findByStudentId(Long id, String examType, String viewType, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findByStudentId(id, Pageable.unpaged());

        List<Exam> exams = new LinkedList<>(examsPages.getContent());
        List<Exam> examsFiltered = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            boolean found = false;
            if (examType.equals("finished") && exam.getExamSchedule().getTimeOfExam().isBefore(LocalDateTime.now())){
                if (viewType.equals("passed") && exam.getPoints() != null && exam.getPoints() > 50){
                    found = true;
                }
                else if (viewType.equals("failed") && (exam.getPoints() == null || exam.getPoints() <51)){
                    found = true;
                }
            }
            else if (examType.equals("unfinished") && exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
                found = true;
            }

            if (!found){
                examsFiltered.remove(exam);
            }
        }

        Page<ExamDTO> examsPageable = new PageImpl<>(examsFiltered, pageable,examsFiltered.size()).map(examMapper::toDto);

        return examsPageable;
    }

    @Override
    public List<Exam> findByStudentId(Long id) {
        return examRepository.findByStudentId(id);
    }

    @Override
    public Page<ExamDTO> findBySyllabusId(Long id, String examType, String viewType, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findByExamScheduleSubjectSyllabusId(id, Pageable.unpaged());

        List<Exam> exams = new LinkedList<>(examsPages.getContent());
        List<Exam> examsFiltered = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            boolean found = false;
            if (examType.equals("finished") && exam.getExamSchedule().getTimeOfExam().isBefore(LocalDateTime.now())){
                if (viewType.equals("passed") && exam.getPoints() != null && exam.getPoints() > 50){
                    found = true;
                }
                else if (viewType.equals("failed") && (exam.getPoints() == null || exam.getPoints() <51)){
                    found = true;
                }
            }
            else if (examType.equals("unfinished") && exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
                found = true;
            }

            if (!found){
                examsFiltered.remove(exam);
            }
        }

        Page<ExamDTO> examsPageable = new PageImpl<>(examsFiltered, pageable,examsFiltered.size()).map(examMapper::toDto);

        return examsPageable;
    }

    @Override
    public Page<ExamDTO> findByStudentIdForTeacher(Long id, Long teacherId, String examType, String viewType, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findByStudentId(id, Pageable.unpaged());

        List<Exam> exams = new LinkedList<>(examsPages.getContent());
        List<Exam> examsFiltered = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            boolean found = false;
            for (Teacher professor : exam.getExamSchedule().getSubject().getProfessors()) {
                if (Objects.equals(professor.getId(), teacherId)) {
                    if (examType.equals("finished") && exam.getExamSchedule().getTimeOfExam().isBefore(LocalDateTime.now())){
                        if (viewType.equals("passed") && exam.getPoints() != null && exam.getPoints() > 50){
                            found = true;
                            break;
                        }
                        else if (viewType.equals("failed") && (exam.getPoints() == null || exam.getPoints() <51)){
                            found = true;
                            break;
                        }
                    }
                    else if (examType.equals("unfinished") && exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                examsFiltered.remove(exam);
            }
        }

        Page<ExamDTO> examsPageable = new PageImpl<>(examsFiltered, pageable,examsFiltered.size()).map(examMapper::toDto);

        return examsPageable;
    }

    @Override
    public Page<ExamDTO> findBySyllabusIdForTeacher(Long id, Long teacherId, String examType, String viewType, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findByExamScheduleSubjectSyllabusId(id, Pageable.unpaged());

        List<Exam> exams = new LinkedList<>(examsPages.getContent());
        List<Exam> examsFiltered = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            boolean found = false;
            for (Teacher professor : exam.getExamSchedule().getSubject().getProfessors()) {
                if (Objects.equals(professor.getId(), teacherId)) {
                    if (examType.equals("finished") && exam.getExamSchedule().getTimeOfExam().isBefore(LocalDateTime.now())){
                        if (viewType.equals("passed") && exam.getPoints() != null && exam.getPoints() > 50){
                            found = true;
                            break;
                        }
                        else if (viewType.equals("failed") && (exam.getPoints() == null || exam.getPoints() <51)){
                            found = true;
                            break;
                        }
                    }
                    else if (examType.equals("unfinished") && exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                examsFiltered.remove(exam);
            }
        }

        Page<ExamDTO> examsPageable = new PageImpl<>(examsFiltered, pageable,examsFiltered.size()).map(examMapper::toDto);

        return examsPageable;
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
    public ExamDTO findOne(Long id) {
        return examMapper.toDto(examRepository.findById(id).orElse(null));
    }

    @Override
    public Page<ExamDTO> findAll(String examType, String viewType, Pageable pageable) {
        Page<Exam> examsPages = examRepository.findAll(Pageable.unpaged());

        List<Exam> exams = new LinkedList<>(examsPages.getContent());
        List<Exam> examsFiltered = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            boolean found = false;
            if (examType.equals("finished") && exam.getExamSchedule().getTimeOfExam().isBefore(LocalDateTime.now())){
                if (viewType.equals("passed") && exam.getPoints() != null && exam.getPoints() > 50){
                    found = true;
                }
                else if (viewType.equals("failed") && (exam.getPoints() == null || exam.getPoints() <51)){
                    found = true;
                }
            }
            else if (examType.equals("unfinished") && exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
                found = true;
            }

            if (!found){
                examsFiltered.remove(exam);
            }
        }

        Page<ExamDTO> examsPageable = new PageImpl<>(examsFiltered, pageable,examsFiltered.size()).map(examMapper::toDto);
        return examsPageable;
    }

    @Override
    public Page<ExamDTO> findAllForTeacher(Long teacherId, String examType,String viewType,Pageable pageable) {
        Page<Exam> examsPages = examRepository.findAll(Pageable.unpaged());

        List<Exam> exams = new LinkedList<>(examsPages.getContent());
        List<Exam> examsFiltered = new LinkedList<>(examsPages.getContent());

        for (Exam exam : exams) {
            boolean found = false;
            for (Teacher professor : exam.getExamSchedule().getSubject().getProfessors()) {
                if (Objects.equals(professor.getId(), teacherId)) {
                    if (examType.equals("finished") && exam.getExamSchedule().getTimeOfExam().isBefore(LocalDateTime.now()) && viewType != null){
                        if (viewType.equals("passed") && exam.getPoints() != null && exam.getPoints() > 50){
                            found = true;
                            break;
                        }
                        else if (viewType.equals("failed") && (exam.getPoints() == null || exam.getPoints() <51)){
                            found = true;
                            break;
                        }
                    }
                    else if (examType.equals("unfinished") && exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                examsFiltered.remove(exam);
            }
        }

        Page<ExamDTO> examsPageable = new PageImpl<>(examsFiltered, pageable,examsFiltered.size()).map(examMapper::toDto);
        return examsPageable;
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public ExamDTO createExamApplication(Long examScheduleId, Long studentId){
        ExamSchedule examSchedule = examScheduleService.findOne(examScheduleId);
        Student student = studentService.findOne(studentId);

        if (examSchedule == null || student == null){
            return null;
        }

        if(student.getFunds() - this.examRegistrationFee < 0){
            return null;
        }

        // updating student's funds
        student.setFunds(student.getFunds() - this.examRegistrationFee);
        // studentService.save(student);

        Exam exam = new Exam();
        exam.setExamSchedule(examSchedule);
        exam.setStudent(student);

        return (examMapper.toDto(save(exam)));
    }

    @Override
    public ExamDTO update(ExamDTO examDTO) {
        Optional<Exam> e = examRepository.findById(examDTO.getId());
        Exam exam;
        if (e.isPresent()){
            exam = e.get();
        }
        else{
            return null;
        }

        if (exam.getExamSchedule().getTimeOfExam().isAfter(LocalDateTime.now())){
            // updating exam schedule
            exam.setExamSchedule(examScheduleMapper.toEntity(examDTO.getExamScheduleDTO()));
        }
        // updating basic info
        exam.setPoints(examDTO.getPoints());

        return examMapper.toDto(save(exam));
    }

    @Override
    public void delete(Long id) {
        if (examRepository.findById(id).isPresent()){
            Exam exam = examRepository.findById(id).get();
            // updating student's funds
            exam.getStudent().setFunds(exam.getStudent().getFunds() + this.examRegistrationFee);
            studentService.save(exam.getStudent());
            examRepository.deleteById(id);
        }
    }
}
