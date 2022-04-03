package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { Student.class, ExamSchedule.class })
public interface ExamMapper extends EntityMapper<ExamDTO, Exam>{
    @Mapping(target = "studentDTO", source = "student")
    @Mapping(target = "studentDTO.userDTO", source = "student.user")
    @Mapping(target = "studentDTO.majorDTO", source = "student.major")
    @Mapping(target = "examScheduleDTO", source = "examSchedule")
    @Mapping(target = "examScheduleDTO.subjectDTO", source = "examSchedule.subject")
    @Mapping(target = "examScheduleDTO.subjectDTO.syllabusDTO", source = "examSchedule.subject.syllabus")
    @Mapping(target = "examScheduleDTO.examinationPeriodDTO", source = "examSchedule.examinationPeriod")
    ExamDTO toDto(Exam s);
}
