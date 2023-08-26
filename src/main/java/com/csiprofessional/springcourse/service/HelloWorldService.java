package com.csiprofessional.springcourse.service;

import com.csiprofessional.springcourse.entity.PersonEntity;
import com.csiprofessional.springcourse.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloWorldService {

    private final PersonRepository personRepository;


    public String talk() {
        return "Talking";
    }


    public void addPersonWhoTalks(String firstname, String lastname, String email,
                                  String address, String city, String phone) {

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname(firstname);
        personEntity.setLastname(lastname);
        personEntity.setEmail(email);
        personEntity.setAddress(address);
        personEntity.setCity(city);
        personEntity.setPhone(phone);

        personRepository.save(personEntity);
    }

    public int howManyTalks() {
        return personRepository.findAllBy().size();
    }
}
