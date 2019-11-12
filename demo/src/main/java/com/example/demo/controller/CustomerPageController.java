package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.CustomerRequest;
import com.example.demo.model.Customer;
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
	public String save(@ModelAttribute(name="cust") Customer cust,
			BindingResult errors, Model model) {
		CustomerRequest request = new CustomerRequest();
		request.setEmail(cust.getEmail());
		request.setBirthDate(cust.getBirthDate());
		request.setName(cust.getName());
		request.setAddress(cust.getAddress());
		service.updateCustomer(request);
		return list("", model);		
	}
}
