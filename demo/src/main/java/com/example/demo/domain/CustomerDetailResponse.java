package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class CustomerDetailResponse {
    private String name;
    private String email;
    private String address;
    
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date birthDate;
    private String message;
}
