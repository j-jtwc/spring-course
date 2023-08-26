package com.csiprofessional.springcourse.dao;

import com.csiprofessional.springcourse.entity.custom.PersonCustomEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonDao {
    private final EntityManager entityManager;

    public PersonCustomEntity getPersonCustomEntityById(Long id) {
        try {
            Query query = entityManager.createNativeQuery("select id, firstname, lastname from person where id =:id",
                    PersonCustomEntity.class);
            query.setParameter("id", id);
            return (PersonCustomEntity) query.getSingleResult();
        } catch (NoResultException n) {
            return null;
        }
    }
}
