package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SyllabusMapper.class})
public interface SubjectMapper {

    @Mapping(target = "syllabusDTO", source = "syllabus")
    SubjectDTO toDto(Subject subject);

    @Mapping(target = "syllabus", source = "syllabusDTO")
    Subject toEntity(SubjectDTO subjectDTO);
}
