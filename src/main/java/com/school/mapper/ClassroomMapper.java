package com.school.mapper;

import com.school.dto.ClassroomDto;
import com.school.entity.ClassroomEntity;
import com.school.request.ClassroomRequest;
import com.school.response.ClassroomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassroomMapper {

    ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);

    ClassroomDto classroomRequestToClassroomDto(ClassroomRequest ClassroomRequest);

    ClassroomResponse classroomDtoToClassroomResponse(ClassroomDto ClassroomDto);

    @Mapping(source = "schoolEntity.id", target = "schoolId")
    @Mapping(source = "scholarSessionEntity.id", target = "scholarSessionId")
    ClassroomDto classroomEntityToClassroomDTO(ClassroomEntity ClassroomEntity);

}
