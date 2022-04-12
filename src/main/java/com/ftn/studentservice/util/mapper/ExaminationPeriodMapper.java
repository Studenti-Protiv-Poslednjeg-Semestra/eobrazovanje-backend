package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.ExaminationPeriod;
import com.ftn.studentservice.web.dto.ExaminationPeriodDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link ExaminationPeriod} and its DTO {@link ExaminationPeriodDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExaminationPeriodMapper extends EntityMapper<ExaminationPeriodDTO, ExaminationPeriod> {
}
