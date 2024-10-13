package com.chrismoran.petsittersapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chrismoran.petsittersapplication.models.LoginUser;
import com.chrismoran.petsittersapplication.models.User;
import com.chrismoran.petsittersapplication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	// Root route to display the Register and Login forms
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	// Post route for registering a user
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model) {
		
		User user = userService.register(newUser, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		session.setAttribute("userId", user.getId());
		return "redirect:/home";
	}
	
	// Post route for logging in an existing user
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result, Model model) {
		User user = userService.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("userId", user.getId());
		return "redirect:/home";
	}
	
	// Route to log out the user
	@PostMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	// Route to show the dashboard
	@GetMapping("/home")
	public String dashboard(Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		User loggedInUser = userService.getOneUser(userId);
		model.addAttribute("loggedInUser", loggedInUser);
		return "dashboard.jsp";
	}
}
