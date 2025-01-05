package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	//User Dashboard Page
	@RequestMapping("/dashboard")
	public String userDashboard() {
		return "user/dashboard";
	}
	//User profile Page
	@RequestMapping("/profile")
	public String userProfile() {
		return "user/profile";
	}
	//Add Contact Page
	
	// User View Page
	
	//User Edit Contact
	
	//User delete Contact
}
