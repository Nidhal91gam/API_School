package com.school.repository;

import com.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.ScholarSessionEntity;

import java.util.List;


@Repository
public interface ScholarSessionRepository extends JpaRepository<ScholarSessionEntity, Long> {

    boolean existsScholarSessionEntityByYearAndSchoolEntity(int year, SchoolEntity SchoolEntity);

    boolean existsScholarSessionEntityById(Long id);

    List<ScholarSessionEntity> findAllBySchoolEntity(SchoolEntity schoolEntity);

}
