package com.csiprofessional.springcourse.dao;

import com.csiprofessional.springcourse.entity.custom.PersonCustomEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updatePersonById(Long id) {
        entityManager.createNativeQuery("update person set firstname = 'test' where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
