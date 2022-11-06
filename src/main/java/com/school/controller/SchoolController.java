package com.school.controller;

import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;
import com.school.mapper.AdministrationMapper;
import com.school.mapper.SchoolMapper;
import com.school.request.AdministrationRequest;
import com.school.response.AdministrationResponse;
import com.school.response.SchoolResponse;
import com.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.school.request.SchoolRequest;

import java.text.ParseException;

@RestController
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    // All school requests

    @PostMapping("/school")
    public ResponseEntity<?> addNewSchool(@RequestBody SchoolRequest schoolRequest) throws ParseException, ItemAlreadyInUseException {
        schoolService.addNewSchool(SchoolMapper.INSTANCE.schoolRequestToSchoolDTO(schoolRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/school")
    public ResponseEntity<?> modifySelectedSchool(@RequestBody SchoolRequest schoolRequest) throws ParseException, ItemAlreadyInUseException {
        schoolService.modifySelectedSchool(SchoolMapper.INSTANCE.schoolRequestToSchoolDTO(schoolRequest));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/school/{id}")
    public ResponseEntity<?> deleteSelectedSchool(@PathVariable(name = "id") Long id) throws ItemAlreadyInUseException, ItemNotFoundException {
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
}
