package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.web.dto.SubjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.web.dto.MajorDTO;

/**
 * Mapper for the entity {@link Major} and its DTO {@link MajorDTO}.
 */
@Mapper(componentModel = "spring", uses = { SyllabusMapper.class })
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {

    @Mapping(target = "syllabusDTO", source = "syllabus")
    SubjectDTO toDto(Subject subject);

    @Mapping(target = "syllabus", source = "syllabusDTO")
    Subject toEntity(SubjectDTO subjectDTO);

}
