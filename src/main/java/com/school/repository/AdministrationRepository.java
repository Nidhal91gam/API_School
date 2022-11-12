package com.school.repository;

import com.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.AdministrationEntity;

import java.util.List;

@Repository
public interface AdministrationRepository extends JpaRepository<AdministrationEntity, Long> {

    boolean existsAdministrationEntityByEmail(String email);

    boolean existsAdministrationEntityById(Long id);

    List<AdministrationEntity> findAllBySchoolEntity(SchoolEntity schoolEntity);

}
