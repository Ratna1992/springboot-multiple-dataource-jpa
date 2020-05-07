package com.ratna.architecture.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.architecture.service.LaunchService;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@RestController
public class LaunchController {
	@Autowired
	LaunchService launchService;
	private Logger logger = LoggerFactory.getLogger(LaunchController.class);
	@GetMapping(value = "/insertData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse saveData() {
		logger.info(ArchitectureUtility.enteredInto("saveData"));
		logger.info("inserts data into user controller triggered");
		logger.info(ArchitectureUtility.exitedFrom("saveData"));
		return launchService.insertUsers();
	}

	@GetMapping(value = "/retrieveData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse retrieveAllData() {
		logger.info(ArchitectureUtility.enteredInto("retrieveAllData"));
		logger.info("retrieves  user data triggered");
		logger.info(ArchitectureUtility.exitedFrom("retrieveAllData"));
		return launchService.getAllUsers();
	}

	@GetMapping(value = "/retrieveAudience", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse retrieveAudienceData() {
		logger.info(ArchitectureUtility.enteredInto("retrieveAudienceData"));
		logger.info("retrieves audience data triggered");
		logger.info(ArchitectureUtility.exitedFrom("retrieveAudienceData"));
		return launchService.getAllAudience();
	}

}
