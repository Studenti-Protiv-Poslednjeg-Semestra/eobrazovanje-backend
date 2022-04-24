package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SubjectMapper.class, ExaminationPeriod.class })
public interface ExamScheduleMapper extends EntityMapper<ExamScheduleDTO, ExamSchedule>{
    @Mapping(target = "subjectDTO", source = "subject")
    @Mapping(target = "subjectDTO.syllabusDTO", source = "subject.syllabus")
    @Mapping(target = "subjectDTO.syllabusDTO.majorDTO", source = "subject.syllabus.major")
    @Mapping(target = "examinationPeriodDTO", source = "examinationPeriod")
    ExamScheduleDTO toDto(ExamSchedule s);

    @Mapping(target = "subject", source = "subjectDTO")
    @Mapping(target = "examinationPeriod", source = "examinationPeriodDTO")
    ExamSchedule toEntity(ExamScheduleDTO s);

}
