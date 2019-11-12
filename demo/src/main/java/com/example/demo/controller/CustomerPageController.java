package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.CustomerRequest;
import com.example.demo.service.CustomerService;

@Controller
@RequestMapping("/customer/page")
public class CustomerPageController{
	
	@Autowired
	private CustomerService service;

	@GetMapping
	public String list(@RequestParam(name="name", defaultValue="", required=false) String name,
			Model model) {
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Direction.ASC, "name", "email"));
		
		model.addAttribute("list", service.getCustomerList(pageRequest).getCustomers().getContent());
		return "customer/customer_list";
	}
	
	@GetMapping("/edit")
	public String detail(@RequestParam(name="email", defaultValue="", required=false) String email, 
			Model model) {
		model.addAttribute("cust", service.getCustomerDetail(email));
		return "customer/customer_edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute(name="cust") CustomerRequest cust,
			BindingResult errors, Model model) {
		if (cust.getBirthDate() == null || new Date().before(cust.getBirthDate())) {
			errors.addError(new FieldError("cust", "birthDate", "Invalid birth day"));
			return "customer/customer_edit";
		}
		service.updateCustomer(cust);
		return "redirect:/customer/page";		
	}
}
