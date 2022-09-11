package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.web.dto.MajorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MajorMapper {

    MajorDTO toDto(Major major);

    // TODO: Dopunite ovo, sta god to bilo. Trazio je da implementiram iz EntityMapper-a.
    List<Major> toEntity(List<MajorDTO> dtoList);

    List<MajorDTO> toDto(List<Major> entityList);

    Major toEntity(MajorDTO majorDTO);
}
