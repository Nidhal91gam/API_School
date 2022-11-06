package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.AdministrationEntity;

@Repository
public interface AdministrationRepository extends JpaRepository<AdministrationEntity, Long> {

    boolean existsAdministrationEntityByEmail(String email);

    boolean existsAdministrationEntityById(Long id);

}
