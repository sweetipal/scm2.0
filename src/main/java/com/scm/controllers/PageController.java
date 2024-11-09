package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	//routes
	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("Home page handler");
		model.addAttribute("FirstName","Sweety");
		model.addAttribute("LastName","Pal");
		model.addAttribute("profile","Java Developer");
		model.addAttribute("linkdin","https://www.linkedin.com/in/sweeti-pal-2b2477240/");
		return "home";
	}
	
	//About route
	@RequestMapping("/about")
	public String aboutPage(Model model) {
		model.addAttribute("isLogin","false");
		System.out.println("About page loading");
		return "about";
	}
	@RequestMapping("/services")
	public String servicePage() {
		System.out.println("Srvices page loading");
		return "services";
	}
	
	
	

}
