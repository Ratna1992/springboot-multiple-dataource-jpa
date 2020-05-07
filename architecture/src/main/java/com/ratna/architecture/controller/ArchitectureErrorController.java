package com.ratna.architecture.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ratna.architecture.utility.ArchitectureUtility;

@Controller
public class ArchitectureErrorController implements ErrorController {
	
	private Logger logger = LoggerFactory.getLogger(ArchitectureErrorController.class);

	@RequestMapping("/error")
	public String handleError() {
		logger.info(ArchitectureUtility.enteredInto("handleError"));
		logger.info("handleError process goes here...");
		logger.info(ArchitectureUtility.exitedFrom("handleError"));
		return "error";
	}

	@Override
	public String getErrorPath() {
		logger.info(ArchitectureUtility.enteredInto("getErrorPath"));
		logger.info("getErrorPath process goes here...");
		logger.info(ArchitectureUtility.exitedFrom("getErrorPath"));
		return "/error";
	}

}
