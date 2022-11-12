package com.school.service;

import com.school.dto.*;
import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;
import com.school.request.ClassroomRequest;

import java.text.ParseException;
import java.util.List;

public interface SchoolService {

    // All school requests

    void addNewSchool(SchoolDto schoolDto) throws ParseException, ItemAlreadyInUseException;

    void modifySelectedSchool(SchoolDto schoolDto) throws ItemNotFoundException, ParseException;

    void deleteSelectedSchool(Long id) throws ItemAlreadyInUseException, ItemNotFoundException;

    SchoolDto getSelectedSchool(Long id) throws ItemNotFoundException;

    // All administration requests

    void addNewAdministration(AdministrationDto administrationDTO) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException;

    void modifySelectedAdministration(AdministrationDto administrationDTO) throws ParseException, ItemNotFoundException, ItemAlreadyInUseException;

    void deleteSelectedAdministration(Long id) throws ItemNotFoundException;

    AdministrationDto getSelectedAdministration(Long id) throws ItemNotFoundException;

    List<AdministrationDto> getAdministrationBySelectedSchool(Long id) throws ItemNotFoundException;

    // all ScholarSession requests

    void addNewScholarSession(ScholarSessionDto scholarSessionDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException;

    void modifySelectedScholarSession(ScholarSessionDto scholarSessionDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException;

    ScholarSessionDto getSelectedScholarSession(Long id) throws ItemNotFoundException;

    void deleteSelectedScholarSession(Long id) throws ItemNotFoundException;

    List<ScholarSessionDto> getScholarSessionBySelectedSchool(Long idSchool) throws ItemNotFoundException;

    // all Classroom requests

    void addNewClassroom(ClassroomDto classroomDto) throws ItemNotFoundException, ItemAlreadyInUseException;

    void modifySelectedClassroom(ClassroomDto classroomDto) throws ItemNotFoundException, ItemAlreadyInUseException;

    void deleteSelectedClassroom(Long id) throws ItemNotFoundException;

    ClassroomDto getSelectedClassroom(Long id) throws ItemNotFoundException;

    List<ClassroomDto> getClassroomBySelectedScholarSession(Long id) throws ItemNotFoundException;

    // all Field requests

    void addNewField(String name) throws ItemAlreadyInUseException;

    void modifySelectedField(Long id, String name) throws ItemNotFoundException, ItemAlreadyInUseException;

    void addFieldToSelectedClassroom(Long idField, Long idClassroom) throws ItemNotFoundException, ItemAlreadyInUseException;

    void deleteSelectedField(Long id) throws ItemNotFoundException;

    FieldDto getSelectedField(Long id) throws ItemNotFoundException;

    List<FieldDto> getFieldBySelectedClassroom(Long idClassroom) throws ItemNotFoundException;

    List<FieldDto> getFieldList();

    // all Student requests

    void addNewStudent(StudentDto studentDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException;

    void modifySelectedStudent(StudentDto studentDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException;

    List<StudentDto> getStudentBySelectedSchool(Long id) throws ItemNotFoundException;

    List<StudentDto> getStudentBySelectedClassroom(Long id) throws ItemNotFoundException;

    StudentDto getSelectedStudent(Long id) throws ItemNotFoundException;

    void deleteSelectedStudent(Long id) throws ItemNotFoundException;

    // all Teacher requests

    void addNewTeacher(TeacherDto teacherDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException;

    void modifySelectedTeacher(TeacherDto teacherDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException;

    TeacherDto getSelectedTeacher(Long id) throws ItemNotFoundException;

    void deleteSelectedTeacher(Long id) throws ItemNotFoundException;

    void addTeacherToSelectedClassroom(Long idTeacher, Long idClassroom) throws ItemNotFoundException, ItemAlreadyInUseException;

}
