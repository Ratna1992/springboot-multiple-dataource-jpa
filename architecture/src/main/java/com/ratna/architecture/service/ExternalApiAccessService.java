package com.ratna.architecture.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratna.architecture.dao.ExternalApiAccessDAO;
import com.ratna.architecture.filereaders.ArchitectureCSVReader;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@Service
public class ExternalApiAccessService {
	@Autowired
	ExternalApiAccessDAO toDoDAO;
	
	@Autowired
	ArchitectureCSVReader csvReader;
	private Logger logger = LoggerFactory.getLogger(ExternalApiAccessService.class);

	public ArchitectureResponse getDataFromJSONService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromJSONService"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		// creating executor service
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		logger.debug("Executor Service started successfully...");
		// calling a method using lambda
		Callable<ArchitectureResponse> architectureResponseFromDAO = () -> toDoDAO.getDataFromJSONService();

		// submitting the request to executorservice
		Future<ArchitectureResponse> future = executorService.submit(architectureResponseFromDAO);

		// getting the response from future
		try {
			architectureResponse = future.get();
		} catch (InterruptedException | ExecutionException e) {

			architectureResponse.setStatusMsg(e.getLocalizedMessage());
			logger.error(e.getMessage());
		}
		// closing the executor service
		finally {
			logger.debug("Executor Service closed successfully...");
			executorService.shutdown();
		}
		logger.info(ArchitectureUtility.exitedFrom("getDataFromJSONService"));
		return architectureResponse;

	}

	public ArchitectureResponse getDataFromXMLService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromXMLService"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		// creating executor service
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		logger.debug("Executor Service started successfully...");
		// calling a method using lambda
		Callable<ArchitectureResponse> architectureResponseFromDAO = () -> toDoDAO.getDataFromXMLService();

		// submitting the request to executorservice
		Future<ArchitectureResponse> future = executorService.submit(architectureResponseFromDAO);

		// getting the response from future
		try {
			architectureResponse = future.get();
		} catch (InterruptedException | ExecutionException e) {

			architectureResponse.setStatusMsg(e.getLocalizedMessage());
			logger.error(e.getMessage());
		}
		// closing the executor service
		finally {
			logger.debug("Executor Service closed successfully...");
			executorService.shutdown();
		}
		logger.info(ArchitectureUtility.exitedFrom("getDataFromXMLService"));
		return architectureResponse;

	}

	public ArchitectureResponse removeCacheFromXMLService() {
		logger.info(ArchitectureUtility.enteredInto("removeCacheFromXMLService"));

		logger.info(ArchitectureUtility.exitedFrom("removeCacheFromXMLService"));
		return toDoDAO.removeCacheFromXMLService();
	}

	public ArchitectureResponse removeCacheFromJSONService() {
		logger.info(ArchitectureUtility.enteredInto("removeCacheFromJSONService"));
		logger.info(ArchitectureUtility.exitedFrom("removeCacheFromJSONService"));
		return toDoDAO.removeCacheFromJSONService();
	}
	
	

}
