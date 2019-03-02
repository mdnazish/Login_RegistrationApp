package com.mn.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Md Nazish
 *
 */
@Controller
public class ApplicationController {
	@RequestMapping("/home")
	public String home() {
		return "home";
	}

}
