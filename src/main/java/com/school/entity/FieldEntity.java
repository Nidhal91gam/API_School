package com.school.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "FIELD_ENTITY")
public class FieldEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "fieldEntities")
	List<ClassroomEntity> classroomEntities = new ArrayList<>();

	public FieldEntity() {
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

	public List<ClassroomEntity> getClassroomEntities() {
		return classroomEntities;
	}

	public void setClassroomEntities(List<ClassroomEntity> classroomEntities) {
		this.classroomEntities = classroomEntities;
	}
}
