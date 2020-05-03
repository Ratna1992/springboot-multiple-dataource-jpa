package com.ratna.architecture.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/")
	public String home() {

		return ("<h1> Welcome Home</h1");
	}

	@GetMapping("/user")
	public String user() {

		return ("<h1> Welcome User</h1");
	}

	@GetMapping("/admin")
	public String admin() {

		return ("<h1> Welcome Admin</h1");
	}

}