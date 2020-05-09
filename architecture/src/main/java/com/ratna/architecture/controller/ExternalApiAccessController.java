package com.ratna.architecture.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.architecture.service.ExternalApiAccessService;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@RestController
public class ExternalApiAccessController {

	@Autowired
	ExternalApiAccessService externalApiAccessService;
	private Logger logger = LoggerFactory.getLogger(ExternalApiAccessController.class);

	@GetMapping(value = "/getDataFromJSONApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse getDataFromJSONService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromJSONService"));
		logger.info("retreive response from  external json service started..");
		logger.info(ArchitectureUtility.exitedFrom("getDataFromJSONService"));
		return externalApiAccessService.getDataFromJSONService();
	}

	@GetMapping(value = "/getDataFromXMLApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse getDataFromXMLService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromXMLService"));
		logger.info("retreive response from  external xml service started..");
		logger.info(ArchitectureUtility.exitedFrom("getDataFromXMLService"));
		return externalApiAccessService.getDataFromXMLService();
	}

	@GetMapping(value = "/clearJSONCacheService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse clearJSONCache() {
		logger.info(ArchitectureUtility.enteredInto("clearJSONCache"));
		logger.info(ArchitectureUtility.exitedFrom("clearJSONCache"));
		return externalApiAccessService.removeCacheFromJSONService();
	}

	@GetMapping(value = "/clearXMLCacheService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse clearXMLCache() {
		logger.info(ArchitectureUtility.enteredInto("clearXMLCache"));
		logger.info(ArchitectureUtility.exitedFrom("clearXMLCache"));
		return externalApiAccessService.removeCacheFromXMLService();
	}

	

}
