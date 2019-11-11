package com.example.demo.domain;

import com.example.demo.model.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class CustomerListResponse {
    private Page<Customer> customers;
}
