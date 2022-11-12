package com.school.controller;

import com.school.dto.*;
import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;
import com.school.mapper.*;
import com.school.request.*;
import com.school.response.*;
import com.school.service.SchoolService;
import com.school.service.SchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchoolController {


    SchoolServiceImpl schoolService;

    public SchoolController(SchoolServiceImpl schoolService) {
        this.schoolService = schoolService;
    }

    // All school requests

    @PostMapping("/school")
    public ResponseEntity<?> addNewSchool(@RequestBody SchoolRequest schoolRequest) throws ParseException, ItemAlreadyInUseException {
        schoolService.addNewSchool(SchoolMapper.INSTANCE.schoolRequestToSchoolDTO(schoolRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/school")
    public ResponseEntity<?> modifySelectedSchool(@RequestBody SchoolRequest schoolRequest) throws ItemNotFoundException, ParseException {
        schoolService.modifySelectedSchool(SchoolMapper.INSTANCE.schoolRequestToSchoolDTO(schoolRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/school/{id}")
    public ResponseEntity<?> deleteSelectedSchool(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        schoolService.deleteSelectedSchool(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<SchoolResponse> getSelectedSchool(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        SchoolResponse schoolResponse = SchoolMapper.INSTANCE.schoolDTOToSchoolResponse(schoolService.getSelectedSchool(id));
        return new ResponseEntity<>(schoolResponse, HttpStatus.ACCEPTED);
    }

    // All administration requests

    @PostMapping("/administration")
    public ResponseEntity<?> addNewAdministration(@RequestBody AdministrationRequest administrationRequest) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.addNewAdministration(AdministrationMapper.INSTANCE.administrationRequestToAdministrationDto(administrationRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/administration")
    public ResponseEntity<?> modifySelectedAdministration(@RequestBody AdministrationRequest administrationRequest) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.modifySelectedAdministration(AdministrationMapper.INSTANCE.administrationRequestToAdministrationDto(administrationRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/administration/{id}")
    public ResponseEntity<?> deleteSelectedAdministration(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        schoolService.deleteSelectedAdministration(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/administration/{id}")
    public ResponseEntity<AdministrationResponse> getSelectedAdministration(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        AdministrationResponse administrationResponse = AdministrationMapper.INSTANCE.administrationDtoToAdministrationResponse(schoolService.getSelectedAdministration(id));
        return new ResponseEntity<>(administrationResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_administration/{idSchool}")
    public ResponseEntity<List<AdministrationResponse>> getAdministrationBySelectedSchool(@PathVariable(name = "idSchool") Long idSchool) throws ItemNotFoundException {
        List<AdministrationDto> administrationDTOS = schoolService.getAdministrationBySelectedSchool(idSchool);
        List<AdministrationResponse> administrationResponses = new ArrayList<>();
        administrationDTOS.forEach(administrationDTO -> {
            administrationResponses.add(AdministrationMapper.INSTANCE.administrationDtoToAdministrationResponse(administrationDTO));
        });
        return new ResponseEntity<>(administrationResponses, HttpStatus.ACCEPTED);
    }

    // all ScholarSession requests

    @PostMapping("/scholar_session")
    public ResponseEntity<?> addNewScholarSession(@RequestBody ScholarSessionRequest scholarSessionRequest) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.addNewScholarSession(ScholarSessionMapper.INSTANCE.scholarSessionRequestToScholarSessionDto(scholarSessionRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/scholar_session")
    public ResponseEntity<?> modifySelectedScholarSession(@RequestBody ScholarSessionRequest scholarSessionRequest) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.modifySelectedScholarSession(ScholarSessionMapper.INSTANCE.scholarSessionRequestToScholarSessionDto(scholarSessionRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/scholar_session/{id}")
    public ResponseEntity<ScholarSessionResponse> getSelectedScholarSession(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        ScholarSessionResponse schoolResponse = ScholarSessionMapper.INSTANCE.scholarSessionDtoToScholarSessionResponse(schoolService.getSelectedScholarSession(id));
        return new ResponseEntity<>(schoolResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_scholar_session/{idSchool}")
    public ResponseEntity<List<ScholarSessionResponse>> getScholarSessionBySelectedSchool(@PathVariable(name = "idSchool") Long idSchool) throws ItemNotFoundException {
        List<ScholarSessionDto> scholarSessionDtos = schoolService.getScholarSessionBySelectedSchool(idSchool);
        List<ScholarSessionResponse> scholarSessionResponses = new ArrayList<>();
        scholarSessionDtos.forEach(scholarSessionDto -> {
            scholarSessionResponses.add(ScholarSessionMapper.INSTANCE.scholarSessionDtoToScholarSessionResponse(scholarSessionDto));
        });
        return new ResponseEntity<>(scholarSessionResponses, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/scholar_session/{id}")
    public ResponseEntity<?> deleteSelectedScholarSession(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        schoolService.deleteSelectedScholarSession(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // all Classroom requests

    @PostMapping("/classroom")
    public ResponseEntity<?> addNewClassroom(@RequestBody ClassroomRequest classroomRequest) throws ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.addNewClassroom(ClassroomMapper.INSTANCE.classroomRequestToClassroomDto(classroomRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/classroom")
    public ResponseEntity<?> modifySelectedClassroom(@RequestBody ClassroomRequest classroomRequest) throws ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.modifySelectedClassroom(ClassroomMapper.INSTANCE.classroomRequestToClassroomDto(classroomRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/classroom/{id}")
    public ResponseEntity<?> deleteSelectedClassroom(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        schoolService.deleteSelectedClassroom(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/classroom/{id}")
    public ResponseEntity<ClassroomResponse> getSelectedClassroom(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        ClassroomResponse classroomResponse = ClassroomMapper.INSTANCE.classroomDtoToClassroomResponse(schoolService.getSelectedClassroom(id));
        return new ResponseEntity<>(classroomResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_classroom/{idScholarSession}")
    public ResponseEntity<List<ClassroomResponse>> getClassroomBySelectedScholarSession(@PathVariable(name = "idScholarSession") Long idScholarSession) throws ItemNotFoundException {
        List<ClassroomDto> classroomDtos = schoolService.getClassroomBySelectedScholarSession(idScholarSession);
        List<ClassroomResponse> classroomResponses = new ArrayList<>();
        classroomDtos.forEach(classroomDto -> {
            classroomResponses.add(ClassroomMapper.INSTANCE.classroomDtoToClassroomResponse(classroomDto));
        });
        return new ResponseEntity<>(classroomResponses, HttpStatus.ACCEPTED);
    }

    // all Field requests

    @PostMapping("/field")
    public ResponseEntity<?> addNewField(@RequestBody String name) throws ItemAlreadyInUseException {
        schoolService.addNewField(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/field/{id}")
    public ResponseEntity<?> modifySelectedField(@RequestBody String name, @PathVariable(name = "id") Long id) throws ItemNotFoundException, ItemAlreadyInUseException {
        schoolService.modifySelectedField(id, name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/field/{idClassroom}")
    public ResponseEntity<?> addFieldToSelectedClassroom(@RequestBody Long idField, @PathVariable(name = "idClassroom") Long idClassroom) throws ItemNotFoundException, ItemAlreadyInUseException {
        schoolService.addFieldToSelectedClassroom(idField, idClassroom);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/field/{id}")
    public ResponseEntity<?> deleteSelectedField(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        schoolService.deleteSelectedField(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/field/{id}")
    public ResponseEntity<FieldResponse> getSelectedField(@PathVariable(name = "id") Long id) throws ItemNotFoundException {
        FieldResponse fieldResponse = FieldMapper.INSTANCE.fieldDtoToFieldResponse(schoolService.getSelectedField(id));
        return new ResponseEntity<>(fieldResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_field/{idClassroom}")
    public ResponseEntity<List<FieldResponse>> getFieldBySelectedClassroom(@PathVariable(name = "idClassroom") Long idClassroom) throws ItemNotFoundException {
        List<FieldDto> fieldDtos = schoolService.getFieldBySelectedClassroom(idClassroom);
        List<FieldResponse> fieldResponses = new ArrayList<>();
        fieldDtos.forEach(fieldDto -> {
            fieldResponses.add(FieldMapper.INSTANCE.fieldDtoToFieldResponse(fieldDto));
        });
        return new ResponseEntity<>(fieldResponses, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_field")
    public ResponseEntity<List<FieldResponse>> getFieldList() {
        List<FieldDto> fieldDtos = schoolService.getFieldList();
        List<FieldResponse> fieldResponses = new ArrayList<>();
        fieldDtos.forEach(fieldDto -> {
            fieldResponses.add(FieldMapper.INSTANCE.fieldDtoToFieldResponse(fieldDto));
        });
        return new ResponseEntity<>(fieldResponses, HttpStatus.ACCEPTED);
    }

    // all Student requests

    @PostMapping("/student")
    public ResponseEntity<?> addNewStudent(@RequestBody StudentRequest studentRequest) throws ItemAlreadyInUseException, ItemNotFoundException, ParseException {
        schoolService.addNewStudent(StudentMapper.INSTANCE.studentRequestToStudentDto(studentRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/student")
    public ResponseEntity<?> modifySelectedStudent(@RequestBody StudentRequest studentRequest) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.modifySelectedStudent(StudentMapper.INSTANCE.studentRequestToStudentDto(studentRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_student_school/{schoolId}")
    public ResponseEntity<List<StudentResponse>> getStudentBySelectedSchool(@PathVariable(name = "schoolId") Long schoolId) throws ItemNotFoundException {
        List<StudentDto> studentDtos = schoolService.getStudentBySelectedSchool(schoolId);
        List<StudentResponse> studentResponses = new ArrayList<>();
        studentDtos.forEach(studentDto -> {
            studentResponses.add(StudentMapper.INSTANCE.studentDtoToStudentResponse(studentDto));
        });
        return new ResponseEntity<>(studentResponses, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_student_classroom/{classroomId}")
    public ResponseEntity<List<StudentResponse>> getStudentBySelectedClassroom(@PathVariable(name = "classroomId") Long classroomId) throws ItemNotFoundException {
        List<StudentDto> studentDtos = schoolService.getStudentBySelectedClassroom(classroomId);
        List<StudentResponse> studentResponses = new ArrayList<>();
        studentDtos.forEach(studentDto -> {
            studentResponses.add(StudentMapper.INSTANCE.studentDtoToStudentResponse(studentDto));
        });
        return new ResponseEntity<>(studentResponses, HttpStatus.ACCEPTED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentResponse> getSelectedStudent(@PathVariable(name = "studentId") Long studentId) throws ItemNotFoundException {
        StudentResponse studentResponse = StudentMapper.INSTANCE.studentDtoToStudentResponse(schoolService.getSelectedStudent(studentId));
        return new ResponseEntity<>(studentResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<?> deleteSelectedStudent(@PathVariable(name = "studentId") Long studentId) throws ItemNotFoundException {
        schoolService.deleteSelectedStudent(studentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // all Teacher requests

    @PostMapping("/teacher")
    public ResponseEntity<?> addNewTeacher(@RequestBody TeacherRequest teacherRequest) throws ItemAlreadyInUseException, ItemNotFoundException, ParseException {
        schoolService.addNewTeacher(TeacherMapper.INSTANCE.teacherRequestToTeacherDto(teacherRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/teacher/{idClassroom}")
    public ResponseEntity<?> addTeacherToSelectedClassroom(@RequestBody Long idTeacher, @PathVariable(name = "idClassroom") Long idClassroom) throws ItemNotFoundException, ItemAlreadyInUseException {
        schoolService.addTeacherToSelectedClassroom(idTeacher, idClassroom);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PutMapping("/teacher")
    public ResponseEntity<?> modifySelectedTeacher(@RequestBody TeacherRequest teacherRequest) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {
        schoolService.modifySelectedTeacher(TeacherMapper.INSTANCE.teacherRequestToTeacherDto(teacherRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<TeacherResponse> getSelectedTeacher(@PathVariable(name = "teacherId") Long teacherId) throws ItemNotFoundException {
        TeacherResponse teacherResponse = TeacherMapper.INSTANCE.teacherDtoToTeacherResponse(schoolService.getSelectedTeacher(teacherId));
        return new ResponseEntity<>(teacherResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/teacher/{teacherId}")
    public ResponseEntity<?> deleteSelectedTeacher(@PathVariable(name = "teacherId") Long teacherId) throws ItemNotFoundException {
        schoolService.deleteSelectedTeacher(teacherId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
