package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String email;
    private String address;
    private Date birthDate;
}
