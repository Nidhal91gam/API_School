package com.school.mapper;

import com.school.dto.TeacherDto;
import com.school.entity.ClassroomEntity;
import com.school.entity.TeacherEntity;
import com.school.request.TeacherRequest;
import com.school.response.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDto teacherRequestToTeacherDto(TeacherRequest TeacherRequest);

    TeacherResponse teacherDtoToTeacherResponse(TeacherDto TeacherDto);

    @Mapping(source = "schoolEntity.id", target = "schoolId")
    @Mapping(source = "classroomEntities", target = "classroomIds", qualifiedByName = "classroomIds")
    TeacherDto teacherEntityToTeacherDTO(TeacherEntity TeacherEntity);

    @Named("classroomIds")
    default List<Long> classroomEntitiesToClassroomIds(List<ClassroomEntity> classroomEntities) {
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
