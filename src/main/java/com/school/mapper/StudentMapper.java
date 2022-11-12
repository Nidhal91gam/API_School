package com.school.mapper;

import com.school.dto.StudentDto;
import com.school.entity.StudentEntity;
import com.school.request.StudentRequest;
import com.school.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto studentRequestToStudentDto(StudentRequest StudentRequest);

    StudentResponse studentDtoToStudentResponse(StudentDto StudentDto);

    @Mapping(source = "schoolEntity.id", target = "schoolId")
    @Mapping(source = "classroomEntity.id", target = "classroomId")
    StudentDto StudentEntityToStudentDTO(StudentEntity StudentEntity);

}
