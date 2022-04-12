package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MajorMapper.class})
public interface SyllabusMapper extends EntityMapper<SyllabusDTO, Syllabus>{

    @Mapping(target = "majorDTO", source = "major")
    SyllabusDTO toDto(Syllabus syllabus);

    @Mapping(target = "major", source = "majorDTO")
    Syllabus toEntity(SyllabusDTO syllabusDTO);

}
