package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Enrollment;
import com.ftn.studentservice.model.ExamSchedule;
import com.ftn.studentservice.model.ExaminationPeriod;
import com.ftn.studentservice.web.dto.EnrollmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SubjectMapper.class })
public interface EnrollmentMapper {

    @Mapping(target = "subjectDTO", source = "subject")
    EnrollmentDTO toDto(Enrollment e);

    //Enrollment toEntity(EnrollmentDTO e);

}
