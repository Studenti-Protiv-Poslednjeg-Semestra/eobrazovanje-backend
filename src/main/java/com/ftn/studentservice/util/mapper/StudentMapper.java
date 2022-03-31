package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, MajorMapper.class})
public interface StudentMapper {

    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "majorDTO", source = "major")
    StudentDTO toDto(Student student);

    Student toEntity(StudentDTO studentDTO);
}
