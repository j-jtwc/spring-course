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

    private void modifyString(String name) {
        name = "A2";
    }
}
