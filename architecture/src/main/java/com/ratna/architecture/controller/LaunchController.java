package com.ratna.architecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.architecture.service.LaunchService;
import com.ratna.architecture.transferobjects.ArchitectureResponse;

@RestController
public class LaunchController {
	@Autowired
	LaunchService launchService;

	@GetMapping(value = "/insertData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse saveData() {
		return launchService.insertUsers();
	}

	@GetMapping(value = "/retrieveData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse retrieveAllData() {
		return launchService.getAllUsers();
	}

	@GetMapping(value = "/retrieveAudience", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse retrieveAudienceData() {
		return launchService.getAllAudience();
	}

}
