package com.ratna.architecture.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.architecture.service.CSVService;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@RestController
public class ParseCsvController {

	@Autowired
	CSVService csvService;
	private Logger logger = LoggerFactory.getLogger(ParseCsvController.class);

	@GetMapping(value = "/readCSVFileData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchitectureResponse readCSVFile() {
		logger.info(ArchitectureUtility.enteredInto("readCSVFile"));
		logger.info(ArchitectureUtility.exitedFrom("readCSVFile"));
		return csvService.fetchCsvDetails();
	}

	@GetMapping(value = "/downloadCSVFile", produces = "text/csv")
	public void downloadCSVFile(HttpServletResponse httpServletResponse) {
		try {
			logger.info(ArchitectureUtility.enteredInto("downloadCSVFile"));
			String csvFileName = "audience.csv";
			httpServletResponse.setContentType("text/csv");
			// creates mock data
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
			httpServletResponse.setHeader(headerKey, headerValue);
			csvService.downloadCSVFile(httpServletResponse.getWriter());
			logger.info(ArchitectureUtility.exitedFrom("downloadCSVFile"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
