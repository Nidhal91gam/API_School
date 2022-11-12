package com.school.service;

import com.school.dto.*;
import com.school.entity.*;
import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;
import com.school.mapper.*;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static com.school.constant.constant.FORMAT_DATE;

@Service
public class SchoolServiceImpl implements SchoolService {

    SchoolRepository schoolRepository;

    AdministrationRepository administrationRepository;

    ScholarSessionRepository scholarSessionRepository;

    ClassroomRepository classroomRepository;

    FieldRepository fieldRepository;

    StudentRepository studentRepository;

    TeacherRepository teacherRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, AdministrationRepository administrationRepository, ScholarSessionRepository scholarSessionRepository, ClassroomRepository classroomRepository, FieldRepository fieldRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.schoolRepository = schoolRepository;
        this.administrationRepository = administrationRepository;
        this.scholarSessionRepository = scholarSessionRepository;
        this.classroomRepository = classroomRepository;
        this.fieldRepository = fieldRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    // All school requests

    @Override
    public void addNewSchool(SchoolDto schoolDto) throws ParseException, ItemAlreadyInUseException {
        if (schoolRepository.existsSchoolEntityByName(schoolDto.getName())) {
            throw new ItemAlreadyInUseException("school name");
        } else {
            SchoolEntity schoolEntity = new SchoolEntity();
            schoolEntity.setName(schoolDto.getName());
            schoolEntity.setAdress(schoolDto.getAdress());
            schoolEntity.setCity(schoolDto.getCity());
            schoolEntity.setCodePostal(schoolDto.getCodePostal());
            schoolEntity.setDateFoundation(new SimpleDateFormat(FORMAT_DATE).parse(schoolDto.getDateFoundation()));
            schoolEntity.setDirector(schoolDto.getDirector());
            schoolRepository.save(schoolEntity);
        }
    }

    @Override
    public void modifySelectedSchool(SchoolDto schoolDto) throws ItemNotFoundException, ParseException {
        SchoolEntity schoolEntity = schoolRepository.findById(schoolDto.getId()).orElseThrow(() -> new ItemNotFoundException("School", "id"));
        if (!schoolEntity.getName().equals(schoolDto.getName())) {
            if (schoolRepository.existsSchoolEntityByName(schoolDto.getName())) {
                try {
                    throw new ItemAlreadyInUseException("school name");
                } catch (ItemAlreadyInUseException e) {
                    throw new RuntimeException(e);
                }
            }
            schoolEntity.setName(schoolDto.getName());
        }
        schoolEntity.setAdress(schoolDto.getAdress());
        schoolEntity.setCity(schoolDto.getCity());
        schoolEntity.setCodePostal(schoolDto.getCodePostal());
        schoolEntity.setDirector(schoolDto.getDirector());
        schoolEntity.setDateFoundation(new SimpleDateFormat(FORMAT_DATE).parse(schoolDto.getDateFoundation()));
        schoolRepository.save(schoolEntity);
    }

    @Override
    public void deleteSelectedSchool(Long id) throws ItemNotFoundException {
        if (!schoolRepository.existsSchoolEntityById(id)) {
            throw new ItemNotFoundException("school", "id");
        } else {
            schoolRepository.deleteById(id);
        }
    }

    @Override
    public SchoolDto getSelectedSchool(Long id) throws ItemNotFoundException {
        SchoolEntity schoolEntity = schoolRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("school", "id"));
        return SchoolMapper.INSTANCE.schoolEntityToSchoolDto(schoolEntity);
    }

    // All administration requests

    @Override
    public void addNewAdministration(AdministrationDto administrationDTO) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {

        if (administrationRepository.existsAdministrationEntityByEmail(administrationDTO.getEmail())) {
            throw new ItemAlreadyInUseException("email");
        } else {
            SchoolEntity schoolEntity = schoolRepository.findById(administrationDTO.getSchoolId()).orElseThrow(() -> new ItemNotFoundException("school", "id"));
            AdministrationEntity administrationEntity = new AdministrationEntity();
            administrationEntity.setFirstName(administrationDTO.getFirstName());
            administrationEntity.setLastName(administrationDTO.getLastName());
            administrationEntity.setAge(administrationDTO.getAge());
            administrationEntity.setDateBirth(new SimpleDateFormat(FORMAT_DATE).parse(administrationDTO.getDateBirth()));
            administrationEntity.setPlaceBirth(administrationDTO.getPlaceBirth());
            administrationEntity.setEmail(administrationDTO.getEmail());
            administrationEntity.setDiploma(administrationDTO.getDiploma());
            administrationEntity.setGraduationDate(new SimpleDateFormat(FORMAT_DATE).parse(administrationDTO.getGraduationDate()));
            administrationEntity.setYearExperience(administrationDTO.getYearExperience());
            administrationEntity.setFunctionality(administrationDTO.getFunctionality());

            // one to many relationship implementation
            administrationEntity.setSchoolEntity(schoolEntity);
            if (schoolEntity.getAdministrationEntities().isEmpty()) {
                schoolEntity.setAdministrationEntities(new ArrayList<>());
            }
            schoolEntity.getAdministrationEntities().add(administrationEntity);
            schoolRepository.save(schoolEntity);

            // save new administration entity
            administrationRepository.save(administrationEntity);
        }
    }

    @Override
    public void modifySelectedAdministration(AdministrationDto administrationDTO) throws ParseException, ItemNotFoundException, ItemAlreadyInUseException {
        AdministrationEntity administrationEntity = administrationRepository.findById(administrationDTO.getId()).orElseThrow(() -> new ItemNotFoundException("administrator", "id"));
        if (!administrationEntity.getEmail().equals(administrationDTO.getEmail())) {
            if (administrationRepository.existsAdministrationEntityByEmail(administrationDTO.getEmail())) {
                throw new ItemAlreadyInUseException("email");
            }
            administrationEntity.setEmail(administrationDTO.getEmail());
        }
        administrationEntity.setFirstName(administrationDTO.getFirstName());
        administrationEntity.setLastName(administrationDTO.getLastName());
        administrationEntity.setAge(administrationDTO.getAge());
        administrationEntity.setDateBirth(new SimpleDateFormat(FORMAT_DATE).parse(administrationDTO.getDateBirth()));
        administrationEntity.setPlaceBirth(administrationDTO.getPlaceBirth());
        administrationEntity.setDiploma(administrationDTO.getDiploma());
        administrationEntity.setGraduationDate(new SimpleDateFormat(FORMAT_DATE).parse(administrationDTO.getGraduationDate()));
        administrationEntity.setYearExperience(administrationDTO.getYearExperience());
        administrationEntity.setFunctionality(administrationDTO.getFunctionality());
        administrationRepository.save(administrationEntity);
    }

    @Override
    public void deleteSelectedAdministration(Long id) throws ItemNotFoundException {
        if (!administrationRepository.existsAdministrationEntityById(id)) {
            throw new ItemNotFoundException("administration", "id");
        } else {
            administrationRepository.deleteById(id);
        }
    }

    @Override
    public AdministrationDto getSelectedAdministration(Long id) throws ItemNotFoundException {
        AdministrationEntity administrationEntity = administrationRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("administration", "id"));
        return AdministrationMapper.INSTANCE.administrationEntityToAdministrationDTO(administrationEntity);
    }

    @Override
    public List<AdministrationDto> getAdministrationBySelectedSchool(Long id) throws ItemNotFoundException {
        SchoolEntity schoolEntity = schoolRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("list of administration", "school id"));
//        List<AdministrationEntity> administrationEntities = schoolEntity.getAdministrationEntities();
        List<AdministrationEntity> administrationEntities = administrationRepository.findAllBySchoolEntity(schoolEntity);
//        return administrationRepository.findAllBySchoolEntity(schoolEntity).stream().map(AdministrationMapper.INSTANCE::administrationEntityToAdministrationDTO).collect(Collectors.toList());
        List<AdministrationDto> administrationDTOS = new ArrayList<>();
        administrationEntities.forEach(administrationEntity -> {
            administrationDTOS.add(AdministrationMapper.INSTANCE.administrationEntityToAdministrationDTO(administrationEntity));
        });
        return administrationDTOS;
    }

    // all ScholarSession requests

    @Override
    public void addNewScholarSession(ScholarSessionDto scholarSessionDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException {
        SchoolEntity schoolEntity = schoolRepository.findById(scholarSessionDto.getSchoolId()).orElseThrow(() -> new ItemNotFoundException("school", "id"));
        if (scholarSessionRepository.existsScholarSessionEntityByYearAndSchoolEntity(scholarSessionDto.getYear(), schoolEntity)) {
            throw new ItemAlreadyInUseException("scholar session year");
        } else {
            ScholarSessionEntity scholarSessionEntity = new ScholarSessionEntity();
            scholarSessionEntity.setName(scholarSessionDto.getName());
            scholarSessionEntity.setYear(scholarSessionDto.getYear());
            scholarSessionEntity.setStartDate(new SimpleDateFormat(FORMAT_DATE).parse(scholarSessionDto.getStartDate()));
            scholarSessionEntity.setEndDate(new SimpleDateFormat(FORMAT_DATE).parse(scholarSessionDto.getEndDate()));

            // one to many relationship implementation
            scholarSessionEntity.setSchoolEntity(schoolEntity);
            if (schoolEntity.getScholarSessionEntities().isEmpty()) {
                schoolEntity.setScholarSessionEntities(new ArrayList<>());
            }
            schoolEntity.getScholarSessionEntities().add(scholarSessionEntity);
            schoolRepository.save(schoolEntity);

            // save new scholarSession entity
            scholarSessionRepository.save(scholarSessionEntity);
        }


    }

    @Override
    public void modifySelectedScholarSession(ScholarSessionDto scholarSessionDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException {
        ScholarSessionEntity scholarSessionEntity = scholarSessionRepository.findById(scholarSessionDto.getId()).orElseThrow(() -> new ItemNotFoundException("scholar session", "id"));
        if (scholarSessionEntity.getYear() != scholarSessionDto.getYear()) {
            if (scholarSessionRepository.existsScholarSessionEntityByYearAndSchoolEntity(scholarSessionDto.getYear(), scholarSessionEntity.getSchoolEntity())) {
                throw new ItemAlreadyInUseException("scholar session year");
            }
            scholarSessionEntity.setYear(scholarSessionDto.getYear());
        }
        scholarSessionEntity.setName(scholarSessionDto.getName());
        scholarSessionEntity.setStartDate(new SimpleDateFormat(FORMAT_DATE).parse(scholarSessionDto.getStartDate()));
        scholarSessionEntity.setEndDate(new SimpleDateFormat(FORMAT_DATE).parse(scholarSessionDto.getEndDate()));
        scholarSessionRepository.save(scholarSessionEntity);

    }

    @Override
    public ScholarSessionDto getSelectedScholarSession(Long id) throws ItemNotFoundException {
        ScholarSessionEntity scholarSessionEntity = scholarSessionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("scholar session", "id"));
        return ScholarSessionMapper.INSTANCE.scholarSessionEntityToScholarSessionDTO(scholarSessionEntity);
    }

    @Override
    public void deleteSelectedScholarSession(Long id) throws ItemNotFoundException {
        if (!scholarSessionRepository.existsScholarSessionEntityById(id)) {
            throw new ItemNotFoundException("scholar session", "id");
        } else {
            scholarSessionRepository.deleteById(id);
        }

    }

    @Override
    public List<ScholarSessionDto> getScholarSessionBySelectedSchool(Long idSchool) throws ItemNotFoundException {
        SchoolEntity schoolEntity = schoolRepository.findById(idSchool).orElseThrow(() -> new ItemNotFoundException("list of scholar session", "school id"));
        List<ScholarSessionEntity> scholarSessionEntities = scholarSessionRepository.findAllBySchoolEntity(schoolEntity);
        List<ScholarSessionDto> scholarSessionDtos = new ArrayList<>();
        scholarSessionEntities.forEach(scholarSessionEntity -> {
            scholarSessionDtos.add(ScholarSessionMapper.INSTANCE.scholarSessionEntityToScholarSessionDTO(scholarSessionEntity));
        });
        return scholarSessionDtos;
    }

    // all Classroom requests

    @Override
    public void addNewClassroom(ClassroomDto classroomDto) throws ItemNotFoundException, ItemAlreadyInUseException {
        ScholarSessionEntity scholarSessionEntity = scholarSessionRepository.findById(classroomDto.getScholarSessionId()).orElseThrow(() -> new ItemNotFoundException("scholar session", "id"));
        if (classroomRepository.existsClassroomEntityByScholarSessionEntityAndName(scholarSessionEntity, classroomDto.getName())) {
            throw new ItemAlreadyInUseException("classroom of selected scholar session");
        } else {
            ClassroomEntity classroomEntity = new ClassroomEntity();
            classroomEntity.setName(classroomDto.getName());
            if (scholarSessionEntity.getClassroomEntities().isEmpty()) {
                scholarSessionEntity.setClassroomEntities(new ArrayList<>());
            }
            scholarSessionEntity.getClassroomEntities().add(classroomEntity);
            classroomEntity.setScholarSessionEntity(scholarSessionEntity);
            scholarSessionRepository.save(scholarSessionEntity);
            if (scholarSessionEntity.getSchoolEntity().getClassroomEntities().isEmpty()) {
                scholarSessionEntity.getSchoolEntity().setClassroomEntities(new ArrayList<>());
            }
            scholarSessionEntity.getSchoolEntity().getClassroomEntities().add(classroomEntity);
            classroomEntity.setSchoolEntity(scholarSessionEntity.getSchoolEntity());
            schoolRepository.save(scholarSessionEntity.getSchoolEntity());
            classroomRepository.save(classroomEntity);
        }
    }

    @Override
    public void modifySelectedClassroom(ClassroomDto classroomDto) throws ItemNotFoundException, ItemAlreadyInUseException {
        ClassroomEntity classroomEntity = classroomRepository.findById(classroomDto.getId()).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        if (!classroomEntity.getName().equals(classroomDto.getName())) {
            if (classroomRepository.existsClassroomEntityByScholarSessionEntityAndName(classroomEntity.getScholarSessionEntity(), classroomDto.getName())) {
                throw new ItemAlreadyInUseException("classroom of selected scholar session");
            }
            classroomEntity.setName(classroomDto.getName());
            classroomRepository.save(classroomEntity);
        }
    }

    @Override
    public void deleteSelectedClassroom(Long id) throws ItemNotFoundException {
        if (!classroomRepository.existsClassroomEntityById(id)) {
            throw new ItemNotFoundException("classroom", "id");
        } else {
            classroomRepository.deleteById(id);
        }
    }

    @Override
    public ClassroomDto getSelectedClassroom(Long id) throws ItemNotFoundException {
        ClassroomEntity classroomEntity = classroomRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        return ClassroomMapper.INSTANCE.classroomEntityToClassroomDTO(classroomEntity);
    }

    @Override
    public List<ClassroomDto> getClassroomBySelectedScholarSession(Long id) throws ItemNotFoundException {
        ScholarSessionEntity scholarSessionEntity = scholarSessionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("list of classes", "scholar session id"));
        List<ClassroomEntity> classroomEntities = classroomRepository.findAllByScholarSessionEntity(scholarSessionEntity);
        List<ClassroomDto> classroomDtos = new ArrayList<>();
        classroomEntities.forEach(classroomEntity -> {
            classroomDtos.add(ClassroomMapper.INSTANCE.classroomEntityToClassroomDTO(classroomEntity));
        });
        return classroomDtos;
    }

    // all Field requests

    @Override
    public void addNewField(String name) throws ItemAlreadyInUseException {
        if (fieldRepository.existsFieldEntityByName(name)) {
            throw new ItemAlreadyInUseException("field name");
        } else {
            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.setName(name);
            fieldRepository.save(fieldEntity);
        }
    }

    @Override
    public void modifySelectedField(Long id, String name) throws ItemNotFoundException, ItemAlreadyInUseException {
        FieldEntity fieldEntity = fieldRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("field", "id"));
        if (fieldRepository.existsFieldEntityByName(name)) {
            throw new ItemAlreadyInUseException("field name");
        } else {
            fieldEntity.setName(name);
            fieldRepository.save(fieldEntity);
        }
    }

    @Override
    public void addFieldToSelectedClassroom(Long idField, Long idClassroom) throws ItemNotFoundException, ItemAlreadyInUseException {
        FieldEntity fieldEntity = fieldRepository.findById(idField).orElseThrow(() -> new ItemNotFoundException("field", "id"));
        ClassroomEntity classroomEntity = classroomRepository.findById(idClassroom).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        if (fieldEntity.getClassroomEntities().contains(classroomEntity)) {
            throw new ItemAlreadyInUseException("classroom");
        } else {
            if (fieldEntity.getClassroomEntities().isEmpty()) {
                fieldEntity.setClassroomEntities(new ArrayList<>());
            }
            fieldEntity.getClassroomEntities().add(classroomEntity);
            if (classroomEntity.getFieldEntities().isEmpty()) {
                classroomEntity.setFieldEntities(new ArrayList<>());
            }
            classroomEntity.getFieldEntities().add(fieldEntity);
            classroomRepository.save(classroomEntity);
            fieldRepository.save(fieldEntity);
        }
    }

    @Override
    public void deleteSelectedField(Long id) throws ItemNotFoundException {
        if (!fieldRepository.existsFieldEntityById(id)) {
            throw new ItemNotFoundException("field", "id");
        } else {
            fieldRepository.deleteById(id);
        }
    }

    @Override
    public FieldDto getSelectedField(Long id) throws ItemNotFoundException {
        FieldEntity fieldEntity = fieldRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("field", "id"));
        return FieldMapper.INSTANCE.fieldEntityToFieldDto(fieldEntity);
    }

    @Override
    public List<FieldDto> getFieldBySelectedClassroom(Long idClassroom) throws ItemNotFoundException {
        ClassroomEntity classroomEntity = classroomRepository.findById(idClassroom).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        List<FieldDto> fieldDtos = new ArrayList<>();
        classroomEntity.getFieldEntities().forEach(fieldEntity -> {
            fieldDtos.add(FieldMapper.INSTANCE.fieldEntityToFieldDto(fieldEntity));
        });
        return fieldDtos;
    }

    @Override
    public List<FieldDto> getFieldList() {
        List<FieldDto> fieldDtos = new ArrayList<>();
        fieldRepository.findAll().forEach(fieldEntity -> {
            fieldDtos.add(FieldMapper.INSTANCE.fieldEntityToFieldDto(fieldEntity));
        });
        return fieldDtos;
    }

    // all Student requests

    @Override
    public void addNewStudent(StudentDto studentDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException {
        ClassroomEntity classroomEntity = classroomRepository.findById(studentDto.getClassroomId()).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        if (studentRepository.existsStudentEntityByEmail(studentDto.getEmail())) {
            throw new ItemAlreadyInUseException("student email");
        } else {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setFirstName(studentDto.getFirstName());
            studentEntity.setLastName(studentDto.getLastName());
            studentEntity.setAge(new Date().getYear() - new SimpleDateFormat(FORMAT_DATE).parse(studentDto.getDateBirth()).getYear());
            studentEntity.setDateBirth(new SimpleDateFormat(FORMAT_DATE).parse(studentDto.getDateBirth()));
            studentEntity.setPlaceBirth(studentDto.getPlaceBirth());
            studentEntity.setEmail(studentDto.getEmail());

            if (classroomEntity.getStudentEntities().isEmpty()) {
                classroomEntity.setStudentEntities(new ArrayList<>());
            }
            classroomEntity.getStudentEntities().add(studentEntity);
            studentEntity.setClassroomEntity(classroomEntity);
            classroomRepository.save(classroomEntity);

            if (classroomEntity.getSchoolEntity().getStudentEntities().isEmpty()) {
                classroomEntity.getSchoolEntity().setStudentEntities(new ArrayList<>());
            }
            classroomEntity.getSchoolEntity().getStudentEntities().add(studentEntity);
            studentEntity.setSchoolEntity(classroomEntity.getSchoolEntity());
            schoolRepository.save(classroomEntity.getSchoolEntity());

            studentRepository.save(studentEntity);
        }
    }

    @Override
    public void modifySelectedStudent(StudentDto studentDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException {
        StudentEntity studentEntity = studentRepository.findById(studentDto.getId()).orElseThrow(() -> new ItemNotFoundException("student", "id"));
        if (!studentEntity.getEmail().equals(studentDto.getEmail())) {
            if (studentRepository.existsStudentEntityByEmail(studentDto.getEmail())) {
                throw new ItemAlreadyInUseException("student email");
            }
            studentEntity.setEmail(studentDto.getEmail());
        }
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setLastName(studentDto.getLastName());
        studentEntity.setAge(new Date().getYear() - new SimpleDateFormat(FORMAT_DATE).parse(studentDto.getDateBirth()).getYear());
        studentEntity.setDateBirth(new SimpleDateFormat(FORMAT_DATE).parse(studentDto.getDateBirth()));
        studentEntity.setPlaceBirth(studentDto.getPlaceBirth());
        studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentDto> getStudentBySelectedSchool(Long id) throws ItemNotFoundException {
        SchoolEntity schoolEntity = schoolRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("School", "id"));
        List<StudentEntity> studentEntities = studentRepository.findAllBySchoolEntity(schoolEntity);
        List<StudentDto> studentDtos = new ArrayList<>();
        studentEntities.forEach(studentEntity -> {
            studentDtos.add(StudentMapper.INSTANCE.StudentEntityToStudentDTO(studentEntity));
        });
        return studentDtos;
    }

    @Override
    public List<StudentDto> getStudentBySelectedClassroom(Long id) throws ItemNotFoundException {
        ClassroomEntity classroomEntity = classroomRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        List<StudentEntity> studentEntities = studentRepository.findAllByClassroomEntity(classroomEntity);

        //


        List<StudentDto> studentDtos = new ArrayList<>();
        studentEntities.forEach(studentEntity -> {
            studentDtos.add(StudentMapper.INSTANCE.StudentEntityToStudentDTO(studentEntity));
        });
        return studentDtos;
    }

    @Override
    public StudentDto getSelectedStudent(Long id) throws ItemNotFoundException {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("student", "id"));
        return StudentMapper.INSTANCE.StudentEntityToStudentDTO(studentEntity);
    }

    @Override
    public void deleteSelectedStudent(Long id) throws ItemNotFoundException {
        if (!studentRepository.existsStudentEntityById(id)) {
            throw new ItemNotFoundException("student", "id");
        } else {
            studentRepository.deleteById(id);
        }
    }

    // all Teacher requests

    @Override
    public void addNewTeacher(TeacherDto teacherDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException {
        SchoolEntity schoolEntity = schoolRepository.findById(teacherDto.getSchoolId()).orElseThrow(() -> new ItemNotFoundException("school", "id"));
        if (studentRepository.existsStudentEntityByEmail(teacherDto.getEmail())) {
            throw new ItemAlreadyInUseException("teacher email");
        } else {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setFirstName(teacherDto.getFirstName());
            teacherEntity.setLastName(teacherDto.getLastName());
            teacherEntity.setAge(new Date().getYear() - new SimpleDateFormat(FORMAT_DATE).parse(teacherDto.getDateBirth()).getYear());
            teacherEntity.setDateBirth(new SimpleDateFormat(FORMAT_DATE).parse(teacherDto.getDateBirth()));
            teacherEntity.setPlaceBirth(teacherDto.getPlaceBirth());
            teacherEntity.setEmail(teacherDto.getEmail());
            teacherEntity.setDiploma(teacherDto.getDiploma());
            teacherEntity.setGraduationDate(new SimpleDateFormat(FORMAT_DATE).parse(teacherDto.getGraduationDate()));
            teacherEntity.setYearExperience(teacherDto.getYearExperience());

            if (schoolEntity.getTeacherEntities().isEmpty()) {
                schoolEntity.setTeacherEntities(new ArrayList<>());
            }
            schoolEntity.getTeacherEntities().add(teacherEntity);
            teacherEntity.setSchoolEntity(schoolEntity);
            schoolRepository.save(schoolEntity);

            teacherRepository.save(teacherEntity);
        }

    }

    @Override
    public void modifySelectedTeacher(TeacherDto teacherDto) throws ItemNotFoundException, ItemAlreadyInUseException, ParseException {
        TeacherEntity teacherEntity = teacherRepository.findById(teacherDto.getId()).orElseThrow(() -> new ItemNotFoundException("teacher", "id"));
        if (!teacherEntity.getEmail().equals(teacherDto.getEmail())) {
            if (teacherRepository.existsTeacherEntityByEmail(teacherDto.getEmail())) {
                throw new ItemAlreadyInUseException("teacher email");
            }
            teacherEntity.setEmail(teacherDto.getEmail());
        }
        teacherEntity.setFirstName(teacherDto.getFirstName());
        teacherEntity.setLastName(teacherDto.getLastName());
        teacherEntity.setAge(new Date().getYear() - new SimpleDateFormat(FORMAT_DATE).parse(teacherDto.getDateBirth()).getYear());
        teacherEntity.setDateBirth(new SimpleDateFormat(FORMAT_DATE).parse(teacherDto.getDateBirth()));
        teacherEntity.setPlaceBirth(teacherDto.getPlaceBirth());
        teacherEntity.setDiploma(teacherDto.getDiploma());
        teacherEntity.setGraduationDate(new SimpleDateFormat(FORMAT_DATE).parse(teacherDto.getGraduationDate()));
        teacherEntity.setYearExperience(teacherDto.getYearExperience());
        teacherRepository.save(teacherEntity);
    }

    @Override
    public TeacherDto getSelectedTeacher(Long id) throws ItemNotFoundException {
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("teacher", "id"));
        return TeacherMapper.INSTANCE.teacherEntityToTeacherDTO(teacherEntity);
    }

    @Override
    public void deleteSelectedTeacher(Long id) throws ItemNotFoundException {
        if (!teacherRepository.existsTeacherEntityById(id)) {
            throw new ItemNotFoundException("teacher", "id");
        } else {
            teacherRepository.deleteById(id);
        }
    }

    @Override
    public void addTeacherToSelectedClassroom(Long idTeacher, Long idClassroom) throws ItemNotFoundException, ItemAlreadyInUseException {
        TeacherEntity teacherEntity = teacherRepository.findById(idTeacher).orElseThrow(() -> new ItemNotFoundException("teacher", "id"));
        ClassroomEntity classroomEntity = classroomRepository.findById(idClassroom).orElseThrow(() -> new ItemNotFoundException("classroom", "id"));
        if (teacherEntity.getClassroomEntities().contains(classroomEntity)) {
            throw new ItemAlreadyInUseException("classroom");
        } else {
            if (teacherEntity.getClassroomEntities().isEmpty()) {
                teacherEntity.setClassroomEntities(new ArrayList<>());
            }
            teacherEntity.getClassroomEntities().add(classroomEntity);
            if (classroomEntity.getTeacherEntities().isEmpty()) {
                classroomEntity.setTeacherEntities(new ArrayList<>());
            }
            classroomEntity.getTeacherEntities().add(teacherEntity);
            classroomRepository.save(classroomEntity);
            teacherRepository.save(teacherEntity);
        }
    }
}
