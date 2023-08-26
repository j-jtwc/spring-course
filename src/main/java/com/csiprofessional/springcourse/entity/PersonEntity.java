package com.csiprofessional.springcourse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;
}
