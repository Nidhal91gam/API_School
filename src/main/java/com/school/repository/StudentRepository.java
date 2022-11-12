package com.school.repository;

import com.school.entity.ClassroomEntity;
import com.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.StudentEntity;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    boolean existsStudentEntityByEmail(String email);

    boolean existsStudentEntityById(Long id);

    List<StudentEntity> findAllBySchoolEntity(SchoolEntity schoolEntity);

    List<StudentEntity> findAllByClassroomEntity(ClassroomEntity classroomEntity);

}
