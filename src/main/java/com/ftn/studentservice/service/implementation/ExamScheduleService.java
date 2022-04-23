package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.repository.ExamScheduleRepository;
import com.ftn.studentservice.service.IExamScheduleService;
import com.ftn.studentservice.util.mapper.ExamScheduleMapper;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamScheduleService implements IExamScheduleService {

    private final ExamScheduleRepository examScheduleRepository;
    private final ExamScheduleMapper examScheduleMapper;
    private final SubjectService subjectService;
    private final ExaminationPeriodService examinationPeriodService;

    public ExamScheduleService(ExamScheduleRepository examScheduleRepository, ExamScheduleMapper examScheduleMapper,
                               SubjectService subjectService, ExaminationPeriodService examinationPeriodService) {
        this.examScheduleRepository = examScheduleRepository;
        this.examScheduleMapper = examScheduleMapper;
        this.subjectService = subjectService;
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
