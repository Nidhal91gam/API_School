package com.school.repository;

import com.school.entity.ClassroomEntity;
import com.school.entity.SchoolEntity;
import com.school.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.TeacherEntity;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    boolean existsTeacherEntityByEmail(String email);

    boolean existsTeacherEntityById(Long id);

    List<TeacherEntity> findAllBySchoolEntity(SchoolEntity schoolEntity);

}
