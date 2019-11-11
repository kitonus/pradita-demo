package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Integer> {
    Customer findByEmail(String email);
}
