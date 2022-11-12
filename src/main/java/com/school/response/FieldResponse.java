package com.school.response;

import java.util.ArrayList;
import java.util.List;

public class FieldResponse {

    private Long id;

    private String name;

    List<Long> classroomIds = new ArrayList<Long>();

    public FieldResponse() {
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

    public List<Long> getClassroomIds() {
        return classroomIds;
    }

    public void setClassroomIds(List<Long> classroomIds) {
        this.classroomIds = classroomIds;
    }
}
