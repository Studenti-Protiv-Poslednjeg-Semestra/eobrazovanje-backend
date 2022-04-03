package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.model.User;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { User.class, Major.class })
public interface StudentMapper extends EntityMapper<StudentDTO, Student>{
    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "majorDTO", source = "major")
    StudentDTO toDto(Student s);
}
