package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.web.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
