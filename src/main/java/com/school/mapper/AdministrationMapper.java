package com.school.mapper;

import com.school.dto.AdministrationDto;
import com.school.entity.AdministrationEntity;
import com.school.request.AdministrationRequest;
import com.school.response.AdministrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrationMapper
{
    AdministrationMapper INSTANCE = Mappers.getMapper(AdministrationMapper.class);
    AdministrationDto administrationRequestToAdministrationDto (AdministrationRequest administrationRequest);

    AdministrationResponse administrationDtoToAdministrationResponse(AdministrationDto administrationDTO);

   @Mapping(source = "schoolEntity.id",target ="schoolId")
   AdministrationDto administrationEntityToAdministrationDTO(AdministrationEntity administrationEntity);
}
