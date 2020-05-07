package com.ratna.architecture.service;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratna.architecture.architecturemodel.Role;
import com.ratna.architecture.architecturemodel.User;
import com.ratna.architecture.dao.LaunchDAO;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.BcryptPasswordGenerator;

@Service
public class LaunchService {
	@Autowired
	LaunchDAO launchDAO;

	public ArchitectureResponse insertUsers() {
		User user = new User();
		user.setAge(26);
		user.setDob(new Date());
		user.setEmailId("admin@admin.com");
		user.setPassword(BcryptPasswordGenerator.encodePassword("admin123"));
		user.setIsActive(Boolean.TRUE);
		user.setName("Ratna Srinivas");
		Role role1 = new Role();
		role1.setRole("ROLE_ADMIN");
		role1.setDescription("Admin OF Organisation");
		Collection<Role> role = user.getRole();
		role.add(role1);
		return launchDAO.insertUsers(user);

	}

	public ArchitectureResponse getAllUsers() {

		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		// creating executor service
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		// calling a method using lambda
		Callable<ArchitectureResponse> architectureResponseFromDAO = () -> launchDAO.getAllUsers();

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

	public ArchitectureResponse getAllAudience() {

		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		// creating executor service
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		// calling a method using lambda
		Callable<ArchitectureResponse> architectureResponseFromDAO = () -> launchDAO.getAllAudience();

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
