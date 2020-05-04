package com.ratna.architecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String home() {

		return "welcome";
	}

	@GetMapping("/user")
	public String user() {

		return "welcomeuser";
	}

	@GetMapping("/admin")
	public String admin() {

		return "welcomeadmin";
	}

}
