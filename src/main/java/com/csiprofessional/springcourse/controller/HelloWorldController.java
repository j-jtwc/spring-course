package com.csiprofessional.springcourse.controller;

import com.csiprofessional.springcourse.entity.PersonEntity;
import com.csiprofessional.springcourse.repository.PersonRepository;
import com.csiprofessional.springcourse.request.HelloWorldRequest;
import com.csiprofessional.springcourse.request.PersonaTalkRequest;
import com.csiprofessional.springcourse.response.CommonResponse;
import com.csiprofessional.springcourse.response.HelloWorldResonse;
import com.csiprofessional.springcourse.response.ObjectReferenceResponse;
import com.csiprofessional.springcourse.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hello")
@Log4j2
@RequiredArgsConstructor
public class HelloWorldController {


    private final HelloWorldService helloWorldService;
    private final PersonRepository personRepository;

    @GetMapping()
    public String sayHello() {
        return helloWorldService.talk();
    }



    @PostMapping()
    public ResponseEntity<?> sayWorld(@RequestBody HelloWorldRequest helloWorldRequest) {
        log.debug("name {}", helloWorldRequest.getName());
        log.info("age {}", helloWorldRequest.getAge());

        HelloWorldResonse helloWorldResonse = new HelloWorldResonse();
        helloWorldResonse.setText("Hello " + helloWorldRequest.getName());
        return new ResponseEntity<>(helloWorldResonse, HttpStatus.CREATED);
    }

    @PostMapping(path = "/updarte-person-talk-update-without-save")
    public ResponseEntity<?> updatePersonTalkWithoutSave() {

//        PersonEntity personEntity = personRepository.findByFirstname("TEST123");
//        personEntity.setAddress("Address 14:11");
//        personRepository.save(personEntity);

        helloWorldService.updateWihtoutSave();

        return new ResponseEntity<>(new CommonResponse("OK"), HttpStatus.OK);
    }

    @PostMapping(path = "/updarte-person-talk")
    public ResponseEntity<?> updatePersonTalk() {

        PersonEntity personEntity = personRepository.findByFirstname("Jiratip2");
        personEntity.setFirstname("TEST123");
        personEntity.setLastname("ABC123");
        personRepository.save(personEntity);

        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }


    @PostMapping(path = "/updarte-person-talk-phone")
    public ResponseEntity<?> updatePersonTalkPhoneByEmail() {

        List<PersonEntity> personEntities = personRepository.findByEmail("jiratip.w@gmail.com");
        personEntities.forEach(personEntity -> {
            personEntity.setPhone("123456789");
        });

//        for (PersonEntity personEntity : personEntities) {
//            personEntity.setPhone("123456789");
//        }

        personRepository.saveAll(personEntities);

        return new ResponseEntity<>(personEntities, HttpStatus.OK);
    }

    @PostMapping(path = "/add-person-talk")
    public ResponseEntity<?> addPersonTalk(@RequestBody PersonaTalkRequest personaTalkRequest) {

        PersonEntity personEntity = helloWorldService.addPersonWhoTalk(
                personaTalkRequest.getFirstname(),
                personaTalkRequest.getLastname(),
                personaTalkRequest.getEmail(),
                personaTalkRequest.getAddress(),
                personaTalkRequest.getCity(),
                personaTalkRequest.getPhone()
        );

        return new ResponseEntity<>(personEntity, HttpStatus.CREATED);
    }


    @PostMapping(path = "/add-person-talks")
    public ResponseEntity<?> addPersonTalks(@RequestBody List<PersonaTalkRequest> personaTalkRequests) {
        List<PersonEntity> personEntities = helloWorldService.addPersonWhoTalks(personaTalkRequests);
        return new ResponseEntity<>(personEntities, HttpStatus.CREATED);
    }


    @PostMapping(path = "/object")
    public ResponseEntity<?> testObjectReference() {

        ObjectReferenceResponse objectReferenceResponse = new ObjectReferenceResponse();
        objectReferenceResponse.setName("Jay");

        modifyObjectRef(objectReferenceResponse);

        return new ResponseEntity<>(objectReferenceResponse, HttpStatus.OK);
    }


    private void modifyObjectRef(ObjectReferenceResponse objectReferenceResponse) {
        objectReferenceResponse.setName("Jay2");

        objectReferenceResponse = new ObjectReferenceResponse();
        objectReferenceResponse.setName("Jay3");
    }
}
