package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PageController {
	@Autowired
	private UserService userService;

	// routes
	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("Home page handler");
		model.addAttribute("FirstName", "Sweety");
		model.addAttribute("LastName", "Pal");
		model.addAttribute("profile", "Java Developer");
		model.addAttribute("linkdin", "https://www.linkedin.com/in/sweeti-pal-2b2477240/");
		return "home";
	}

	// About route
	@RequestMapping("/about")
	public String aboutPage(Model model) {
		model.addAttribute("isLogin", "false");
		System.out.println("About page loading");
		return "about";
	}

	@RequestMapping("/services")
	public String servicePage() {
		System.out.println("Srvices page loading");
		return "services";
	}

	@RequestMapping("/contact")
	public String contactPage() {
		System.out.println("Srvices page loading");
		return "contact";
	}

	@RequestMapping("/login")
	public String loginPage() {
		System.out.println("Srvices page loading");
		return "login";
	}

	@RequestMapping("/register")
	public String signupPage(Model model) {
		UserForm userForm = new UserForm();

		model.addAttribute("userForm", userForm);
		return "register";
	}

	// Processing register
	@RequestMapping(value = "/do-register", method = RequestMethod.POST)
	public String processRegister(@ModelAttribute UserForm userForm,  HttpSession session) {
		//Fetch the form data
		System.out.println(userForm);

		//To do
		//validate form datasession
		//save to db
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setAbout(userForm.getAbout());
		user.setPhoneNumber(userForm.getPhoneNumber());
		//user.setEnabled(true);
		user.setProfilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Ffree-photos-vectors%2Fdefault-profile&psig=AOvVaw1IY_lNe2IohYut2p6YsZDm&ust=1732644033312000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCKC4-5OI-IkDFQAAAAAdAAAAABAJ");
		User savedUser = userService.saveUser(user);
		System.out.println("User saved...");
		//message
		Message message = new Message();
		message.setContent("Registered Successfully...");
		message.setType(MessageType.green);
		session.setAttribute("message",message);
		//redirct to login page
		return "redirect:/register";
	}
}
