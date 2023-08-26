package com.csiprofessional.springcourse.service;

import com.csiprofessional.springcourse.entity.PersonEntity;
import com.csiprofessional.springcourse.repository.PersonRepository;
import com.csiprofessional.springcourse.request.PersonaTalkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelloWorldService {

    private final PersonRepository personRepository;


    public String talk() {
        return "Talking";
    }


    public PersonEntity addPersonWhoTalk(String firstname, String lastname, String email,
                                         String address, String city, String phone) {

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname(firstname);
        personEntity.setLastname(lastname);
        personEntity.setEmail(email);
        personEntity.setAddress(address);
        personEntity.setCity(city);
        personEntity.setPhone(phone);

        personRepository.save(personEntity);

        return personEntity;
    }


    public List<PersonEntity> addPersonWhoTalks(List<PersonaTalkRequest> personaTalkRequestList) {
        List<PersonEntity> personEntityList = personaTalkRequestList.stream().map(personaTalkRequest -> {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setFirstname(personaTalkRequest.getFirstname());
            personEntity.setLastname(personaTalkRequest.getLastname());
            personEntity.setEmail(personaTalkRequest.getEmail());
            personEntity.setAddress(personaTalkRequest.getAddress());
            personEntity.setCity(personaTalkRequest.getCity());
            personEntity.setPhone(personaTalkRequest.getPhone());
            return personEntity;
        }).toList();

        personRepository.saveAll(personEntityList);

        return personEntityList;
    }

    public int howManyTalks() {
        return personRepository.findAllBy().size();
    }

    @Transactional
    public void updateWihtoutSave() {
        PersonEntity personEntity = personRepository.findByFirstname("TEST123");
        personEntity.setAddress("Address 14:12");
    }
}
