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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.*;


@Builder
@Entity(name = "SCHOOL_ENTITY")
public class SchoolEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date dateFoundation;

	private String adress;

	private String city;

	private String codePostal;

	@Transient
	private int studentNumber;

	@Transient
	private int teacherNumber;

	@Transient
	private int scholarSessionNumber;

	@Transient
	private int administrationNumber;

	@Column(unique = true, nullable = false)
	private String director;

	@OneToMany(mappedBy = "schoolEntity")
	List<ScholarSessionEntity> scholarSessionEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "schoolEntity")
	List<AdministrationEntity> administrationEntities = new ArrayList<>();

	@OneToMany(mappedBy = "schoolEntity")
	List<TeacherEntity> TeacherEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "schoolEntity")
	List<StudentEntity> StudentEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "schoolEntity")
	List<ClassroomEntity> ClassroomEntities = new ArrayList<>();

	public SchoolEntity() {
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

	public Date getDateFoundation() {
		return dateFoundation;
	}

	public void setDateFoundation(Date dateFoundation) {
		this.dateFoundation = dateFoundation;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(int teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	public int getScholarSessionNumber() {
		return scholarSessionNumber;
	}

	public void setScholarSessionNumber(int scholarSessionNumber) {
		this.scholarSessionNumber = scholarSessionNumber;
	}

	public int getAdministrationNumber() {
		return administrationNumber;
	}

	public void setAdministrationNumber(int administrationNumber) {
		this.administrationNumber = administrationNumber;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<ScholarSessionEntity> getScholarSessionEntities() {
		return scholarSessionEntities;
	}

	public void setScholarSessionEntities(List<ScholarSessionEntity> scholarSessionEntities) {
		this.scholarSessionEntities = scholarSessionEntities;
	}

	public List<AdministrationEntity> getAdministrationEntities() {
		return administrationEntities;
	}

	public void setAdministrationEntities(List<AdministrationEntity> administrationEntities) {
		this.administrationEntities = administrationEntities;
	}

	public List<TeacherEntity> getTeacherEntities() {
		return TeacherEntities;
	}

	public void setTeacherEntities(List<TeacherEntity> teacherEntities) {
		TeacherEntities = teacherEntities;
	}

	public List<StudentEntity> getStudentEntities() {
		return StudentEntities;
	}

	public void setStudentEntities(List<StudentEntity> studentEntities) {
		StudentEntities = studentEntities;
	}

	public List<ClassroomEntity> getClassroomEntities() {
		return ClassroomEntities;
	}

	public void setClassroomEntities(List<ClassroomEntity> classroomEntities) {
		ClassroomEntities = classroomEntities;
	}
}
