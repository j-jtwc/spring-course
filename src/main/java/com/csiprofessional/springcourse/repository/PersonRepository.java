package com.csiprofessional.springcourse.repository;


import com.csiprofessional.springcourse.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    public PersonEntity findByFirstname(String firstname);
    public List<PersonEntity> findByEmail(String email);
    public List<PersonEntity> findAllBy();
    public List<PersonEntity> findByFirstnameAndLastname(String firstname, String lastname);
}
