package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;
import com.ftn.studentservice.web.dto.ExaminationPeriodDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { Subject.class, ExaminationPeriod.class })
public interface ExamScheduleMapper extends EntityMapper<ExamScheduleDTO, ExamSchedule>{
    @Mapping(target = "subjectDTO", source = "subject")
    @Mapping(target = "examinationPeriodDTO", source = "examinationPeriod")
    ExamScheduleDTO toDto(ExamSchedule s);

}
