package com.ratna.architecture.service;

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
		user.setEmailId("user@user.com");
		user.setPassword("user123");
		user.setIsActive(Boolean.TRUE);
		user.setName("Ratna Srinivas");
		Role role1 = new Role();
		role1.setRole("ROLE_USER");
		role1.setDescription("User OF Organisation");
		Collection<Role> role = user.getRole();
		role.add(role1);
		return launchDAO.insertUsers(user);

	}

	public ArchitectureResponse getAllUsers() {
		return launchDAO.getAllUsers();
	}

	public ArchitectureResponse getAllAudience() {
		return launchDAO.getAllAudience();
	}

}
