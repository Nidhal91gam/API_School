package com.school.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "STUDENT_ENTITY")
public class StudentEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private int age;

	@Temporal(TemporalType.DATE)
	private Date dateBirth;

	private String placeBirth;

	@Column(unique = true, nullable = false)
	private String email;

	@ManyToOne
	private SchoolEntity schoolEntity;
	
	@ManyToOne
	private ClassroomEntity classroomEntity;

	public StudentEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getPlaceBirth() {
		return placeBirth;
	}

	public void setPlaceBirth(String placeBirth) {
		this.placeBirth = placeBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SchoolEntity getSchoolEntity() {
		return schoolEntity;
	}

	public void setSchoolEntity(SchoolEntity schoolEntity) {
		this.schoolEntity = schoolEntity;
	}

	public ClassroomEntity getClassroomEntity() {
		return classroomEntity;
	}

	public void setClassroomEntity(ClassroomEntity classroomEntity) {
		this.classroomEntity = classroomEntity;
	}
}
