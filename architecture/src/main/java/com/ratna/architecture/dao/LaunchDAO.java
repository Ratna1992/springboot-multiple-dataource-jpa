package com.ratna.architecture.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ratna.architecture.architecturemodel.User;
import com.ratna.architecture.testmodel.Audience;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@Repository
@Transactional
public class LaunchDAO {
	@PersistenceContext(unitName = "architectureSchema")
	private EntityManager architectureSchema;

	@PersistenceContext(unitName = "testSchema")
	private EntityManager testSchema;

	private Logger logger = LoggerFactory.getLogger(LaunchDAO.class);

	public ArchitectureResponse insertUsers(User user) {
		logger.info(ArchitectureUtility.enteredInto("insertUsers"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		String msg = "User saved Successfully with Email " + user.getEmailId();
		try {
			logger.info("inserting users into database");
			architectureResponse.setStatusCode(200);
			logger.info("inserted successfully..");
			architectureSchema.persist(user);
		} catch (Exception e) {
			logger.info("problem in insertion..");
			architectureResponse.setStatusCode(400);
			msg = e.getMessage();
			logger.error(e.getMessage());
		}
		architectureResponse.setStatusMsg(msg);
		logger.info(ArchitectureUtility.exitedFrom("insertUsers"));
		return architectureResponse;
	}

	@SuppressWarnings("unchecked")
	public ArchitectureResponse getAllUsers() {
		logger.info(ArchitectureUtility.enteredInto("getAllUsers"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		List<User> users = Collections.emptyList();
		try {
			logger.info("retrieving users from database");
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("SUCCESS");
			users = architectureSchema.createQuery("From User ").getResultList();
			logger.info("retrieved successfully..");
		} catch (Exception e) {
			logger.info("problem while retrieving..");
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		architectureResponse.setResponse(users);
		logger.info(ArchitectureUtility.exitedFrom("getAllUsers"));
		return architectureResponse;
	}

	@SuppressWarnings("unchecked")
	public ArchitectureResponse getAllAudience() {
		logger.info(ArchitectureUtility.enteredInto("getAllAudience"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		List<Audience> audience = Collections.emptyList();
		try {
			logger.info("retrieving users from database");
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("SUCCESS");
			audience = testSchema.createQuery("From Audience ").getResultList();
			logger.info("retrieved successfully..");
		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		architectureResponse.setResponse(audience);
		logger.info(ArchitectureUtility.exitedFrom("getAllAudience"));
		return architectureResponse;
	}

}
