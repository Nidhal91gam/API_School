package com.school.service;

import com.school.dto.AdministrationDTO;
import com.school.dto.SchoolDto;
import com.school.entity.AdministrationEntity;
import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;
import com.school.mapper.AdministrationMapper;
import com.school.mapper.SchoolMapper;
import com.school.repository.AdministrationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.entity.SchoolEntity;

import com.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import static com.school.constant.constant.FORMAT_DATE;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    AdministrationRepository administrationRepository;

    // All school requests

    @Override
    public void addNewSchool(SchoolDto schoolDto) throws ParseException, ItemAlreadyInUseException {
        if (schoolRepository.existsSchoolEntityByName(schoolDto.getName())) {
            throw new ItemAlreadyInUseException("school name");
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE, Locale.FRANCE);
            SchoolEntity schoolEntity = new SchoolEntity();
            schoolEntity.setName(schoolDto.getName());
            schoolEntity.setAdress(schoolDto.getAdress());
            schoolEntity.setCity(schoolDto.getCity());
            schoolEntity.setCodePostal(schoolDto.getCodePostal());
            if (!schoolDto.getDateFoundation().isEmpty()) {
                schoolEntity.setDateFoundation(formatter.parse(schoolDto.getDateFoundation()));
            }
            schoolEntity.setDirector(schoolDto.getDirector());
            schoolRepository.save(schoolEntity);
        }
    }

    @Override
    public void modifySelectedSchool(SchoolDto schoolDto) {
        Optional<SchoolEntity> schoolEntityOptional = schoolRepository.findById(schoolDto.getId());
        schoolEntityOptional.ifPresent(schoolEntity -> {
            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE, Locale.FRANCE);
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
            if (!schoolDto.getDateFoundation().isEmpty()) {
                try {
                    schoolEntity.setDateFoundation(formatter.parse(schoolDto.getDateFoundation()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            schoolRepository.save(schoolEntity);
        });
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
    public void addNewAdministration(AdministrationDTO administrationDTO) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException {

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
    public void modifySelectedAdministration(AdministrationDTO administrationDTO) throws ParseException, ItemNotFoundException, ItemAlreadyInUseException {
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
    public AdministrationDTO getSelectedAdministration(Long id) throws ItemNotFoundException {
        AdministrationEntity administrationEntity = administrationRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("administration", "id"));
        return AdministrationMapper.INSTANCE.administrationEntityToAdministrationDTO(administrationEntity);
    }

}
