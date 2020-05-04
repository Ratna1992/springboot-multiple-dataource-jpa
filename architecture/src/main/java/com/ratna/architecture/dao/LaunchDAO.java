package com.ratna.architecture.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ratna.architecture.architecturemodel.User;
import com.ratna.architecture.testmodel.Audience;
import com.ratna.architecture.transferobjects.ArchitectureResponse;

@Repository
@Transactional
public class LaunchDAO {
	@PersistenceContext(unitName = "architectureSchema")
	private EntityManager architectureSchema;

	@PersistenceContext(unitName = "testSchema")
	private EntityManager testSchema;

	public ArchitectureResponse insertUsers(User user) {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		String msg = "User saved Successfully with Email "+user.getEmailId();
		try {
			architectureResponse.setStatusCode(200);
			architectureSchema.persist(user);
		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			msg = e.getMessage();
		}
		architectureResponse.setStatusMsg(msg);
		return architectureResponse;
	}

	@SuppressWarnings("unchecked")
	public ArchitectureResponse getAllUsers() {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		List<User> users = Collections.emptyList();
		try {
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("SUCCESS");
			users = architectureSchema.createQuery("From User ").getResultList();
		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
		}
		architectureResponse.setResponse(users);
		return architectureResponse;
	}

	@SuppressWarnings("unchecked")
	public ArchitectureResponse getAllAudience() {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		List<Audience> audience = Collections.emptyList();
		try {
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("SUCCESS");
			audience = testSchema.createQuery("From Audience ").getResultList();
		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
		}
		architectureResponse.setResponse(audience);
		return architectureResponse;
	}

}
