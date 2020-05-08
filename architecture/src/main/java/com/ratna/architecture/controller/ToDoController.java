package com.ratna.architecture.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.architecture.service.ToDAOService;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@RestController
public class ToDoController {

	@Autowired
	ToDAOService toDAOService;
	private Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@GetMapping(value = "/getDataFromJSONService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse getDataFromJSONService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromJSONService"));
		logger.info("retreive response from  external json service started..");
		logger.info(ArchitectureUtility.exitedFrom("getDataFromJSONService"));
		return toDAOService.getDataFromJSONService();
	}

	@GetMapping(value = "/getDataFromXMLService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse getDataFromXMLService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromXMLService"));
		logger.info("retreive response from  external xml service started..");
		logger.info(ArchitectureUtility.exitedFrom("getDataFromXMLService"));
		return toDAOService.getDataFromXMLService();
	}

	@GetMapping(value = "/clearJSONCache", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse clearJSONCache() {
		logger.info(ArchitectureUtility.enteredInto("clearJSONCache"));
		logger.info(ArchitectureUtility.exitedFrom("clearJSONCache"));
		return toDAOService.removeCacheFromJSONService();
	}

	@GetMapping(value = "/clearXMLCache", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse clearXMLCache() {
		logger.info(ArchitectureUtility.enteredInto("clearXMLCache"));
		logger.info(ArchitectureUtility.exitedFrom("clearXMLCache"));
		return toDAOService.removeCacheFromXMLService();
	}

}
