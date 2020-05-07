package com.ratna.architecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.architecture.service.ToDAOService;
import com.ratna.architecture.transferobjects.ArchitectureResponse;

@RestController
public class ToDoController {

	@Autowired
	ToDAOService toDAOService;

	@GetMapping(value = "/getDataFromJSONService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse getDataFromJSONService() {
		return toDAOService.getDataFromJSONService();
	}

	@GetMapping(value = "/getDataFromXMLService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse getDataFromXMLService() {
		return toDAOService.getDataFromXMLService();
	}

}
