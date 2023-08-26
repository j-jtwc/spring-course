package com.csiprofessional.springcourse.dao;

import com.csiprofessional.springcourse.entity.custom.PeronRoomCustomEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonRoomDao {
    private final EntityManager entityManager;

    public PeronRoomCustomEntity getPersonRoomCustomEntityById(Long id) {
        Query query = entityManager.createNativeQuery("select pr.id,\n" +
                "p.firstname ,\n" +
                "p.lastname ,\n" +
                "r.name roomname,\n" +
                "r.seat \n" +
                "from person_room pr \n" +
                "left join person p on p.id  = pr.person_id \n" +
                "left join room r on r.id = pr.room_id ", PeronRoomCustomEntity.class);
        return (PeronRoomCustomEntity) query.getSingleResult();
    }
}
