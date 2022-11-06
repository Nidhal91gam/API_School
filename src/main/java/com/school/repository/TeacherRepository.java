package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.TeacherEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long>{

}
