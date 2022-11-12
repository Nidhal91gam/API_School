package com.school.mapper;

import com.school.dto.ScholarSessionDto;
import com.school.entity.ScholarSessionEntity;
import com.school.request.ScholarSessionRequest;
import com.school.response.ScholarSessionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScholarSessionMapper {

    ScholarSessionMapper INSTANCE = Mappers.getMapper(ScholarSessionMapper.class);

    ScholarSessionDto scholarSessionRequestToScholarSessionDto(ScholarSessionRequest scholarSessionRequest);

    ScholarSessionResponse scholarSessionDtoToScholarSessionResponse(ScholarSessionDto scholarSessionDto);

    @Mapping(source = "schoolEntity.id", target = "schoolId")
    ScholarSessionDto scholarSessionEntityToScholarSessionDTO(ScholarSessionEntity scholarSessionEntity);

}
