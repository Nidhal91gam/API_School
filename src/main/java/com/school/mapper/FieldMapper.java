package com.school.mapper;

import com.school.dto.FieldDto;
import com.school.dto.ScholarSessionDto;
import com.school.entity.ClassroomEntity;
import com.school.entity.FieldEntity;
import com.school.entity.ScholarSessionEntity;
import com.school.response.FieldResponse;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface FieldMapper {

    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);


    FieldResponse fieldDtoToFieldResponse(FieldDto fieldDto);

    @Mapping(source = "classroomEntities", target = "classroomIds", qualifiedByName = "classroomIds")
    FieldDto fieldEntityToFieldDto(FieldEntity fieldEntity);


    @Named("classroomIds")
    default List<Long> classroomEntitiesToclassroomIds(List<ClassroomEntity> classroomEntities) {
        if (classroomEntities == null) {
            return null;
        }
        List<Long> classroomIds = new ArrayList<Long>();
        classroomEntities.forEach(classroomEntity -> {
            classroomIds.add(classroomEntity.getId());
        });
        return classroomIds;
    }

}
