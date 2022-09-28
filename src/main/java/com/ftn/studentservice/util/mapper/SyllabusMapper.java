package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.web.dto.SyllabusCreationDTO;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MajorMapper.class})
public interface SyllabusMapper {

    @Mapping(target = "majorDTO", source = "major")
    SyllabusDTO toDto(Syllabus syllabus);

    @Mapping(target = "major", source = "majorDTO")
    Syllabus toEntity(SyllabusDTO syllabusDTO);

    @Mapping(source = "majorId", target = "major.id")
    @Mapping(source = "yearOfCreation", target = "yearOfCreation")
    Syllabus toEntity(SyllabusCreationDTO syllabusCreationDTO);

}
