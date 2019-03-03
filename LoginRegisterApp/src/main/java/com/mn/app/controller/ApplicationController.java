package com.mn.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mn.app.model.User;
import com.mn.app.service.UserService;

/**
 * @author Md Nazish
 *
 */
@Controller
public class ApplicationController {

	@Autowired
	private UserService userService;

	@RequestMapping({ "/", "/home", "/index" })
	public String home(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "home";
	}

	@RequestMapping("/register")
	public String registerUser(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "home";
	}

	@PostMapping("/save-user")
	public String saveUser(@ModelAttribute User user, BindingResult result, HttpServletRequest request) {

		// save user into DB
		userService.saveUser(user);

		// return to login page
		request.setAttribute("mode", "MODE_LOGIN");
		return "redirect:home?action=register";
	}

	@GetMapping("/show-users")
	public String allUser(HttpServletRequest request) {
		// call business method
		request.setAttribute("users", userService.getAllUser());
		request.setAttribute("mode", "ALL_USERS");
		return "home";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam("id") int userId, HttpServletRequest request) {

		// call business method for user deletion
		userService.deleteUser(userId);
		// fetch all existed user and show
		request.setAttribute("users", userService.getAllUser());

		request.setAttribute("mode", "ALL_USERS");
		return "home";
	}

	@RequestMapping("/edit-user")
	public String editUser(@RequestParam("id") int userId, HttpServletRequest request) {

		// call business method for user update an existed user.
		// Recommended: to add get() method otherwise RE: javax.el.PropertyNotFoundException
		request.setAttribute("user", userService.updateUser(userId).get());

		request.setAttribute("mode", "MODE_UPDATE");
		return "home";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "home";
	}
	
	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
			request.setAttribute("mode", "USER_DASHBOARD");
			return "home";
		}else {
			request.setAttribute("error", "Invalid Username & Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "home";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGOUT");
		return "home";
	}

}
