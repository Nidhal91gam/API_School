package com.school.mapper;

import com.school.dto.SchoolDto;
import com.school.entity.SchoolEntity;
import com.school.request.SchoolRequest;
import com.school.response.SchoolResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchoolMapper {

    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    SchoolDto schoolRequestToSchoolDTO(SchoolRequest schoolRequest);

    SchoolResponse schoolDTOToSchoolResponse(SchoolDto schoolDto);

    SchoolDto schoolEntityToSchoolDto(SchoolEntity schoolEntity);

}
