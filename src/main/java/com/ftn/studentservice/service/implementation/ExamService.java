package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.repository.ExamRepository;
import com.ftn.studentservice.service.IExamService;
import com.ftn.studentservice.util.mapper.ExamMapper;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamService implements IExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final EnrollmentService enrollmentService;
    private final SyllabusService syllabusService;
    private final StudentService studentService;
    private final ExamScheduleService examScheduleService;

    public ExamService(ExamRepository examRepository, ExamMapper examMapper, EnrollmentService enrollmentService,
                       SyllabusService syllabusService, StudentService studentService, ExamScheduleService examScheduleService) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
        this.enrollmentService = enrollmentService;
        this.syllabusService = syllabusService;
        this.studentService = studentService;
        this.examScheduleService = examScheduleService;
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
    public ExamDTO findOne(Long id) {
        return examMapper.toDto(examRepository.findById(id).orElse(null));
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
    public ExamDTO createExamApplication(Long examScheduleId, Long studentId){
        ExamSchedule examSchedule = examScheduleService.findOne(examScheduleId);
        Student student = studentService.findOne(studentId);

        if (examSchedule == null || student == null){
            return null;
        }

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

        // updating basic info
        exam.setPoints(examDTO.getPoints());

        // updating exam schedule
        exam.getExamSchedule().setTimeOfExam(examDTO.getExamScheduleDTO().getTimeOfExam());
        exam.getExamSchedule().setPlace(examDTO.getExamScheduleDTO().getPlace());

        // updating examination period
        exam.getExamSchedule().getExaminationPeriod().setName(examDTO.getExamScheduleDTO().getExaminationPeriodDTO().getName());
        exam.getExamSchedule().getExaminationPeriod().setStartDate(examDTO.getExamScheduleDTO().getExaminationPeriodDTO().getStartDate());
        exam.getExamSchedule().getExaminationPeriod().setEndDate(examDTO.getExamScheduleDTO().getExaminationPeriodDTO().getEndDate());

        return examMapper.toDto(save(exam));
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}
