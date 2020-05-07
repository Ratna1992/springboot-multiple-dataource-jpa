package com.ratna.architecture.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratna.architecture.dao.ToDoDAO;
import com.ratna.architecture.transferobjects.ArchitectureResponse;

@Service
public class ToDAOService {
	@Autowired
	ToDoDAO toDoDAO;

	public ArchitectureResponse getDataFromJSONService() {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		// creating executor service
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		// calling a method using lambda
		Callable<ArchitectureResponse> architectureResponseFromDAO = () -> toDoDAO.getDataFromJSONService();

		// submitting the request to executorservice
		Future<ArchitectureResponse> future = executorService.submit(architectureResponseFromDAO);

		// getting the response from future
		try {
			architectureResponse = future.get();
		} catch (InterruptedException | ExecutionException e) {

			architectureResponse.setStatusMsg(e.getLocalizedMessage());
		}
		// closing the executor service
		finally {
			executorService.shutdown();
		}
		return architectureResponse;

	}
	
	public ArchitectureResponse getDataFromXMLService() {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		// creating executor service
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		// calling a method using lambda
		Callable<ArchitectureResponse> architectureResponseFromDAO = () -> toDoDAO.getDataFromXMLService();

		// submitting the request to executorservice
		Future<ArchitectureResponse> future = executorService.submit(architectureResponseFromDAO);

		// getting the response from future
		try {
			architectureResponse = future.get();
		} catch (InterruptedException | ExecutionException e) {

			architectureResponse.setStatusMsg(e.getLocalizedMessage());
		}
		// closing the executor service
		finally {
			executorService.shutdown();
		}
		return architectureResponse;

	}


}
