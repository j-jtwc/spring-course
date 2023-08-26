package com.csiprofessional.springcourse.controller;

import com.csiprofessional.springcourse.request.HelloWorldRequest;
import com.csiprofessional.springcourse.request.PersonaTalkRequest;
import com.csiprofessional.springcourse.response.CommonResponse;
import com.csiprofessional.springcourse.response.HelloWorldResonse;
import com.csiprofessional.springcourse.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        helloWorldService.addPersonWhoTalks(
                personaTalkRequest.getFirstname(),
                personaTalkRequest.getLastname(),
                personaTalkRequest.getEmail(),
                personaTalkRequest.getAddress(),
                personaTalkRequest.getCity(),
                personaTalkRequest.getPhone()
        );

        CommonResponse commonResponse =  new CommonResponse();
        commonResponse.setStatus("OK");
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }
}
