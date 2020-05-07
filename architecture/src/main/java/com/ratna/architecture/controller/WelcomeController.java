package com.ratna.architecture.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ratna.architecture.utility.ArchitectureUtility;

@Controller
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@GetMapping("/")
	public String home() {
		logger.info(ArchitectureUtility.enteredInto("home"));
		logger.info("gives homepage as html response..");
		logger.info(ArchitectureUtility.exitedFrom("home"));
		return "welcome";
	}

	@GetMapping("/user")
	public String user() {
		logger.info(ArchitectureUtility.enteredInto("user"));
		logger.info("gives userpage as html response..");
		logger.info(ArchitectureUtility.exitedFrom("user"));
		return "welcomeuser";
	}

	@GetMapping("/admin")
	public String admin() {
		logger.info(ArchitectureUtility.enteredInto("admin"));
		logger.info("gives adminpage as html response..");
		logger.info(ArchitectureUtility.exitedFrom("admin"));
		return "welcomeadmin";
	}

}
