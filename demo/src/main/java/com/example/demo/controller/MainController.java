package com.example.demo.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private ServletContext servletContext;

	@GetMapping
	public RedirectView redirectToCustomerList() {
		return new RedirectView(servletContext.getContextPath()+"/customer/page");
	}
}
