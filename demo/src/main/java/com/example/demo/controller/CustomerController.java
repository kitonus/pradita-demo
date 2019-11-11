package com.example.demo.controller;

import com.example.demo.domain.CustomerDetailResponse;
import com.example.demo.domain.CustomerListResponse;
import com.example.demo.domain.CustomerRequest;
import com.example.demo.domain.CustomerResponse;
import com.example.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customerList")
    public CustomerListResponse getCustomerlist(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "count", defaultValue = "10", required = false) int count,
            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
            @RequestParam(value = "sort", defaultValue = "name", required = false) String sortProperty) {
        return customerService.getCustomerList(PageRequest.of(page, count, Sort.by(direction, sortProperty)));
    }

    @GetMapping("/customerDetail")
    public CustomerDetailResponse getCustomerDetail(@RequestParam(value = "email", defaultValue = "null", required = true) String email){
        return customerService.getCustomerDetail(email);
    }

    @PostMapping("/createCustomer")
    public CustomerResponse registCustomer(@RequestBody CustomerRequest request){
        return customerService.createCustomer(request);
    }

    @PostMapping("/updateCustomer")
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest request){
        return customerService.updateCustomer(request);
    }

    @PostMapping("/deleteCustomer")
    public CustomerResponse deleteCustomer(@RequestBody CustomerRequest request){
        return customerService.deleteCustomer(request);
    }
}
