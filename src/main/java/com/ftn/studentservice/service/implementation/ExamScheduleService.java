package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.repository.ExamScheduleRepository;
import com.ftn.studentservice.service.IExamScheduleService;
import com.ftn.studentservice.util.mapper.ExamScheduleMapper;
import com.ftn.studentservice.web.dto.ExamDTO;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamScheduleService implements IExamScheduleService {

    private final ExamScheduleRepository examScheduleRepository;
    private final ExamScheduleMapper examScheduleMapper;
    private final SubjectService subjectService;
    private final ExamService examService;
    private final EnrollmentService enrollmentService;
    private final ExaminationPeriodService examinationPeriodService;

    public ExamScheduleService(ExamScheduleRepository examScheduleRepository, ExamScheduleMapper examScheduleMapper,
                               SubjectService subjectService, @Lazy ExamService examService, EnrollmentService enrollmentService,
                               ExaminationPeriodService examinationPeriodService) {
        this.examScheduleRepository = examScheduleRepository;
        this.examScheduleMapper = examScheduleMapper;
        this.subjectService = subjectService;
        this.examService = examService;
        this.enrollmentService = enrollmentService;
        this.examinationPeriodService = examinationPeriodService;
    }


    @Override
    public ExamSchedule findOne(Long id) {
        return examScheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExamScheduleDTO> findAll() {
        return examScheduleRepository.findAll().stream().map(examScheduleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ExamScheduleDTO> findBySubjectId(Long subjectId) {
        List<ExamSchedule> examSchedules = examScheduleRepository.findBySubjectId(subjectId);
        List<ExamSchedule> examSchedulesFiltered = examScheduleRepository.findBySubjectId(subjectId);

        // removing exam schedules that expired
        for (ExamSchedule examSchedule : examSchedules){
            if (examSchedule.getTimeOfExam().isBefore(LocalDateTime.now())){
                examSchedulesFiltered.remove(examSchedule);
            }
        }

        return examSchedulesFiltered.stream().map(examScheduleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ExamScheduleDTO> findAllForStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentService.findByStudentId(studentId);
        List<ExamSchedule> examSchedules = new LinkedList<>();
        List<ExamSchedule> examSchedulesFiltered = new LinkedList<>();

        for (Enrollment enrollment : enrollments){
            examSchedules.addAll(examScheduleRepository.findBySubjectId(enrollment.getSubject().getId()));
            examSchedulesFiltered.addAll(examScheduleRepository.findBySubjectId(enrollment.getSubject().getId()));
        }

        List<ExamDTO> examsForStudent = new LinkedList<>();
        examsForStudent.addAll(examService.findByStudentId(studentId,"unfinished","unpassed", Pageable.unpaged()).getContent());

        // adding only passed exams so that the examSchedules for passed subjects can later be filtered
        // and not shown to student for registration again
        examsForStudent.addAll(examService.findByStudentId(studentId,"finished","passed",Pageable.unpaged()).getContent());

        for (ExamSchedule examSchedule : examSchedules){
            if (examSchedule.getTimeOfExam().isBefore(LocalDateTime.now())){
                examSchedulesFiltered.remove(examSchedule);
                continue;
            }
            for (ExamDTO examDTO : examsForStudent) {
                if (Objects.equals(examSchedule.getSubject().getId(), examDTO.getExamScheduleDTO().getSubjectDTO().getId())) {
                    examSchedulesFiltered.remove(examSchedule);
                    break;
                }
            }
        }

        return examSchedulesFiltered.stream().map(examScheduleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Boolean teacherAuthorizedForSubject(Long teacherId, Long subjectId){
        Subject subject = subjectService.findOne(subjectId);
        boolean authorized = false;
        for (Teacher teacher : subject.getProfessors()) {
            if (Objects.equals(teacher.getId(), teacherId)) {
                authorized = true;
                break;
            }
        }
        return authorized;
    }

    @Override
    public ExamScheduleDTO save(ExamScheduleDTO examScheduleDTO) {
        System.out.println(examScheduleMapper.toEntity(examScheduleDTO));

        ExamSchedule examSchedule = new ExamSchedule();
        examSchedule.setTimeOfExam(examScheduleDTO.getTimeOfExam());
        examSchedule.setPlace(examScheduleDTO.getPlace());

        Subject subject = subjectService.findOne(examScheduleDTO.getSubjectDTO().getId());
        ExaminationPeriod examinationPeriod = examinationPeriodService.findOne(examScheduleDTO.getExaminationPeriodDTO().getId());

        examSchedule.setSubject(subject);
        examSchedule.setExaminationPeriod(examinationPeriod);

        return examScheduleMapper.toDto(examScheduleRepository.save(examSchedule));
    }

    @Override
    public void delete(Long id) {
        examScheduleRepository.deleteById(id);
    }
}
