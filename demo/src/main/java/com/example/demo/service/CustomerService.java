package com.example.demo.service;

import com.example.demo.domain.CustomerDetailResponse;
import com.example.demo.domain.CustomerListResponse;
import com.example.demo.domain.CustomerRequest;
import com.example.demo.domain.CustomerResponse;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepo customerRepo;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CustomerListResponse getCustomerList(PageRequest pageRequest) {
        CustomerListResponse customerListResponse = new CustomerListResponse();
        try {
            customerListResponse.setCustomers(customerRepo.findAll(pageRequest));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error: " + e);
        }
        return customerListResponse;
    }

    public CustomerDetailResponse getCustomerDetail(String email) {
        CustomerDetailResponse customerDetailResponse = new CustomerDetailResponse();
        try {
            Customer customer = customerRepo.findByEmail(email);

            //check if email customer exist, show detail data. If not, send error message
            if (customer != null) {
                customerDetailResponse.setName(customer.getName());
                customerDetailResponse.setAddress(customer.getAddress());
                customerDetailResponse.setEmail(customer.getEmail());
                customerDetailResponse.setBirthDate(customer.getBirthDate());

                //set response if success
                customerDetailResponse.setMessage("Successfull get details customer with email: " + customer.getEmail());
            } else {
                customerDetailResponse.setMessage("Customer doen't exist with email: " + email);
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error Update: " + e);
            customerDetailResponse.setMessage("Failed get details customer with error: " + e);
        }
        return customerDetailResponse;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            Customer customer = customerRepo.findByEmail(request.getEmail());

            //check if email customer not exist, create data. If not, send error message
            if (customer != null) {
                customerResponse.setMessage("Customer already exist with email: " + request.getEmail());
            } else {
                Customer newCustomer = new Customer();
                newCustomer.setName(request.getName());
                newCustomer.setAddress(request.getAddress());
                newCustomer.setEmail(request.getEmail());
                newCustomer.setBirthDate(request.getBirthDate());

                //save data to DB
                customerRepo.save(newCustomer);

                //set response if success
                customerResponse.setName(request.getName());
                customerResponse.setMessage("Successfull create new customer with ID: " + newCustomer.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error  Create: " + e);
            customerResponse.setMessage("Failed create new customet with error: " + e);
        }
        return customerResponse;
    }

    public CustomerResponse updateCustomer(CustomerRequest request) {
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            Customer customer = customerRepo.findByEmail(request.getEmail());

            //check if email customer exist, update data. If not, send error message
            if (customer != null) {
                customer.setId(customer.getId());
                if (request.getName() != null) customer.setName(request.getName());
                if (request.getAddress() != null) customer.setAddress(request.getAddress());
                if (request.getEmail() != null) customer.setEmail(request.getEmail());
                if (request.getBirthDate() != null) customer.setBirthDate(request.getBirthDate());

                //update data to DB
                customerRepo.save(customer);

                //set response if success
                customerResponse.setName(request.getName());
                customerResponse.setMessage("Successfull update customer with email: " + customer.getEmail());
            } else {
                customerResponse.setName(request.getName());
                customerResponse.setMessage("Customer doen't exist with email: " + request.getEmail());
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error Update: " + e);
            customerResponse.setMessage("Failed update customer with error: " + e);
        }
        return customerResponse;
    }

    public CustomerResponse deleteCustomer(CustomerRequest request) {
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            Customer customer = customerRepo.findByEmail(request.getEmail());

            //check if email customer exist, delete data. If not, send error message
            if (customer != null) {

                //delete data to DB
                customerRepo.delete(customer);

                //set response if delete process success
                customerResponse.setMessage("Successfull delete customer with email: " + customer.getEmail());
            } else {
                customerResponse.setMessage("Customer doen't exist with email: " + request.getEmail());
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error Delete: " + e);
            customerResponse.setMessage("Failed delete customer with error: " + e);
        }
        return customerResponse;
    }
}
