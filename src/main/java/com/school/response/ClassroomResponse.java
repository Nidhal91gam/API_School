package com.school.response;

public class ClassroomResponse {

    private Long id;

    private String name;

    private Long numberStudent;

    private Long scholarSessionId;

    private Long schoolId;

    public ClassroomResponse() {
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

    public Long getScholarSessionId() {
        return scholarSessionId;
    }

    public void setScholarSessionId(Long scholarSessionId) {
        this.scholarSessionId = scholarSessionId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
