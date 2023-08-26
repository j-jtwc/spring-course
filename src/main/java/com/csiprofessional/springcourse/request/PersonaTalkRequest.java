package com.csiprofessional.springcourse.request;

import lombok.Data;

@Data
public class PersonaTalkRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String city;
    private String phone;
}
