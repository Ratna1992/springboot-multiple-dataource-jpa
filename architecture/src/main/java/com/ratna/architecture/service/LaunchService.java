package com.ratna.architecture.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratna.architecture.architecturemodel.Role;
import com.ratna.architecture.architecturemodel.User;
import com.ratna.architecture.dao.LaunchDAO;
import com.ratna.architecture.transferobjects.ArchitectureResponse;

@Service
public class LaunchService {
	@Autowired
	LaunchDAO launchDAO;

	public ArchitectureResponse insertUsers() {
		User user = new User();
		user.setAge(26);
		user.setDob(new Date());
		user.setEmailId("Lilly@gmail.com");
		user.setIsActive(Boolean.TRUE);
		user.setName("Lilly");
		Role role1 = new Role();
		role1.setRole("ADMIN");
		role1.setDescription("Admin OF Organisation");

		Role role2 = new Role();
		role2.setRole("USER");
		role2.setDescription("User OF Organisation");
		Collection<Role> role = user.getRole();
		role.addAll(Arrays.asList(role1, role2));
		return launchDAO.insertUsers(user);

	}

	public ArchitectureResponse getAllUsers() {
		return launchDAO.getAllUsers();
	}
	
	public ArchitectureResponse getAllAudience() {
		return launchDAO.getAllAudience();
	}

}
