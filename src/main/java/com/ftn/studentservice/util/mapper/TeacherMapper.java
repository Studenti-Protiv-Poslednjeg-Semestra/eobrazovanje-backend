package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.web.dto.TeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SubjectMapper.class})
public interface TeacherMapper {

    @Mapping(target = "userDTO", source = "user")
    TeacherDTO toDto(Teacher teacher);

    @Mapping(target = "user", source = "userDTO")
    Teacher toEntity(TeacherDTO teacherDTO);
}
