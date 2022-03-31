package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.web.dto.MajorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MajorMapper {

    MajorDTO toDto(Major major);

    Major toEntity(MajorDTO majorDTO);
}
