package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { Major.class })
public interface SyllabusMapper extends EntityMapper<SyllabusDTO, Syllabus>{
	@Mapping(target = "majorDTO", source = "major")
    SyllabusDTO toDto(Syllabus s);
}
