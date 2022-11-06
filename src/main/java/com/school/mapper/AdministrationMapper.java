package com.school.mapper;

import com.school.dto.AdministrationDTO;
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
    AdministrationDTO administrationRequestToAdministrationDto (AdministrationRequest administrationRequest);

    AdministrationResponse administrationDtoToAdministrationResponse(AdministrationDTO administrationDTO);

   @Mapping(source = "schoolEntity.id",target ="schoolId")
    AdministrationDTO administrationEntityToAdministrationDTO(AdministrationEntity administrationEntity);
}
