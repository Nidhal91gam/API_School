package com.school.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "SCHOLARSESSION_ENTITY")
public class ScholarSessionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	private int year;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@ManyToOne
	private SchoolEntity schoolEntity;
	
	@OneToMany(mappedBy = "scholarSessionEntity")
	List<ClassroomEntity> classroomEntities= new ArrayList<>();

	public ScholarSessionEntity() {
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public SchoolEntity getSchoolEntity() {
		return schoolEntity;
	}

	public void setSchoolEntity(SchoolEntity schoolEntity) {
		this.schoolEntity = schoolEntity;
	}

	public List<ClassroomEntity> getClassroomEntities() {
		return classroomEntities;
	}

	public void setClassroomEntities(List<ClassroomEntity> classroomEntities) {
		this.classroomEntities = classroomEntities;
	}
}
