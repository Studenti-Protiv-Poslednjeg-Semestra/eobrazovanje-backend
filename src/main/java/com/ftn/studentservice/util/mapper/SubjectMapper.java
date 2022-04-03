package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.web.dto.MajorDTO;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Major} and its DTO {@link MajorDTO}.
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {
}
