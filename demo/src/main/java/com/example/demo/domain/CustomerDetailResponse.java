package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CustomerDetailResponse {
    private String name;
    private String email;
    private String address;
    private Date birthDate;
    private String message;
}
