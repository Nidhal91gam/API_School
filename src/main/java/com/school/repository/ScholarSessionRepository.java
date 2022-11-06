package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.ScholarSessionEntity;


@Repository
public interface ScholarSessionRepository extends JpaRepository< ScholarSessionEntity, Long>{

}
