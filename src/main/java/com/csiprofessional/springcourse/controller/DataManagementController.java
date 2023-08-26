package com.csiprofessional.springcourse.controller;

import com.csiprofessional.springcourse.dao.PersonDao;
import com.csiprofessional.springcourse.dao.PersonRoomDao;
import com.csiprofessional.springcourse.repository.PersonRepository;
import com.csiprofessional.springcourse.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/data-management")
@RequiredArgsConstructor
public class DataManagementController {
    private final PersonDao personDao;
    private final PersonRoomDao personRoomDao;
    private final PersonRepository personRepository;

    @GetMapping(path = "/person")
    public ResponseEntity<?> getPerson() {
        return ResponseEntity.ok(personDao.getPersonCustomEntityById(2L));
    }


    @GetMapping(path = "/person-room")
    public ResponseEntity<?> getPersonRoom() {
        return ResponseEntity.ok(personRoomDao.getPersonRoomCustomEntityById(1L));
    }

    @GetMapping(path = "/all-person")
    public ResponseEntity<?> getAllPerson() {
        return ResponseEntity.ok(personRepository.getAllPerson(2L));
    }

    @PostMapping(path = "/update-by-sql-entity-manager")
    public ResponseEntity<?> updateBySQL() {
        personDao.updatePersonById(2L);
        return new ResponseEntity<>(new CommonResponse("OK"), HttpStatus.OK);
    }



}
