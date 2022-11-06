package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.school.entity.SchoolEntity;

import java.util.List;


@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {

    boolean existsSchoolEntityByName(String name);

    boolean existsSchoolEntityById(Long id);

}
