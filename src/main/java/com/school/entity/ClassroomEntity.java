package com.school.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "CLASSROOM_ENTITY")
public class ClassroomEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Transient
    private Long numberStudent;

    @ManyToOne
    private SchoolEntity schoolEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "classroom_field", joinColumns = @JoinColumn(name = "classroom_id"), inverseJoinColumns = @JoinColumn(name = "field_id"))
    List<FieldEntity> fieldEntities = new ArrayList<>();

    @OneToMany(mappedBy = "classroomEntity")
    List<StudentEntity> studentEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "classroom_teacher", joinColumns = @JoinColumn(name = "classroom_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    List<TeacherEntity> teacherEntities = new ArrayList<>();

    @ManyToOne
    private ScholarSessionEntity scholarSessionEntity;

    public ClassroomEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(Long numberStudent) {
        this.numberStudent = numberStudent;
    }

    public SchoolEntity getSchoolEntity() {
        return schoolEntity;
    }

    public void setSchoolEntity(SchoolEntity schoolEntity) {
        this.schoolEntity = schoolEntity;
    }

    public List<FieldEntity> getFieldEntities() {
        return fieldEntities;
    }

    public void setFieldEntities(List<FieldEntity> fieldEntities) {
        this.fieldEntities = fieldEntities;
    }

    public List<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(List<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }

    public List<TeacherEntity> getTeacherEntities() {
        return teacherEntities;
    }

    public void setTeacherEntities(List<TeacherEntity> teacherEntities) {
        this.teacherEntities = teacherEntities;
    }

    public ScholarSessionEntity getScholarSessionEntity() {
        return scholarSessionEntity;
    }

    public void setScholarSessionEntity(ScholarSessionEntity scholarSessionEntity) {
        this.scholarSessionEntity = scholarSessionEntity;
    }
}
