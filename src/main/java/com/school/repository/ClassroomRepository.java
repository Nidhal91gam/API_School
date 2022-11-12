package com.school.repository;

import com.school.entity.ScholarSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.ClassroomEntity;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
    Boolean existsClassroomEntityByScholarSessionEntityAndName(ScholarSessionEntity scholarSessionEntity, String name);

    Boolean existsClassroomEntityById(Long id);

    List<ClassroomEntity> findAllByScholarSessionEntity(ScholarSessionEntity scholarSessionEntity);

}
