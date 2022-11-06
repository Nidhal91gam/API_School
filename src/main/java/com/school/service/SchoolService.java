package com.school.service;

import com.school.dto.AdministrationDTO;
import com.school.dto.SchoolDto;
import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;

import java.text.ParseException;

public interface SchoolService {

    void addNewSchool(SchoolDto schoolDto) throws ParseException, ItemAlreadyInUseException;

    void modifySelectedSchool(SchoolDto schoolDto);

    void deleteSelectedSchool(Long id) throws ItemAlreadyInUseException, ItemNotFoundException;

    SchoolDto getSelectedSchool(Long id) throws ItemNotFoundException;

    void  addNewAdministration(AdministrationDTO administrationDTO) throws ParseException, ItemAlreadyInUseException, ItemNotFoundException;
    void modifySelectedAdministration(AdministrationDTO administrationDTO) throws ParseException, ItemNotFoundException, ItemAlreadyInUseException;

    void deleteSelectedAdministration(Long id) throws ItemNotFoundException;

    AdministrationDTO getSelectedAdministration(Long id) throws ItemNotFoundException;
}
