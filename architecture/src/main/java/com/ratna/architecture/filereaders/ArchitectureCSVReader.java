package com.ratna.architecture.filereaders;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.ratna.architecture.testmodel.Audience;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.transferobjects.User;
import com.ratna.architecture.utility.ArchitectureUtility;

@Repository
public class ArchitectureCSVReader {
	private Logger logger = LoggerFactory.getLogger(ArchitectureCSVReader.class);
	@Autowired
	ResourceLoader resourceLoader;
	@PersistenceContext(unitName = "testSchema")
	private EntityManager testSchema;

	public ArchitectureResponse csvReader() {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		logger.info(ArchitectureUtility.enteredInto("csvReader"));
		try {
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("Success");
			Resource resource = resourceLoader.getResource("classpath:demo-csv-file.csv");
			File file = resource.getFile();
			Reader reader = new FileReader(file);
			CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader).withType(User.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<User> userList = new ArrayList<>();
			csvToBean.forEach(user -> {
				logger.debug(user + "");
				userList.add(user);
			});
			architectureResponse.setResponse(userList);

		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg("Failed");
			architectureResponse.setResponse(e.getMessage());
			logger.error(e.getMessage());
		}

		logger.info(ArchitectureUtility.exitedFrom("csvReader"));
		return architectureResponse;
	}

	@SuppressWarnings("unchecked")
	public ArchitectureResponse downloadCSVFile() {
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		logger.info(ArchitectureUtility.enteredInto("downloadCSVFile"));
		List<Audience> resultList = Collections.emptyList();
		try {
			Query createQuery = testSchema.createQuery("From Audience");
			resultList = createQuery.getResultList();
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("Success");
			architectureResponse.setResponse(resultList);

		} catch (Exception ex) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg("Failed");
			architectureResponse.setResponse(ex.getMessage());
			logger.error("Error mapping Bean to CSV", ex);
		}
		logger.info(ArchitectureUtility.exitedFrom("downloadCSVFile"));
		return architectureResponse;
	}

}
