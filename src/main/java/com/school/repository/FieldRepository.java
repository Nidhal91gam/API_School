package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.FieldEntity;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {

    Boolean existsFieldEntityByName(String name);

    Boolean existsFieldEntityById(Long id);

}
