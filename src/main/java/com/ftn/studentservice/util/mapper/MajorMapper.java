package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.web.dto.MajorDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Major} and its DTO {@link MajorDTO}.
 */
@Mapper(componentModel = "spring")
public interface MajorMapper extends EntityMapper<MajorDTO, Major> {

    MajorDTO toDto(Major major);

    Major toEntity(MajorDTO majorDTO);

}
