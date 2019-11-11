package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name="test_customer")
@Getter
@Setter
public class Customer {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(length = 50)
    private String name;

    @Column(length = 50, unique = true, nullable = false)
    @NotNull
    private String email;

    @Column(length = 255)
    private String address;

    private Date birthDate;

}
