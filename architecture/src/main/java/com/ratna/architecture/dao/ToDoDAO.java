package com.ratna.architecture.dao;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.transferobjects.Employees;
import com.ratna.architecture.transferobjects.ToDoTO;
import com.ratna.architecture.utility.ArchitectureUtility;

@Repository
public class ToDoDAO {
	@Autowired
	Environment prop;
	@Autowired
	RestTemplate restTemplate;
	private Logger logger = LoggerFactory.getLogger(ToDoDAO.class);
	@Cacheable("getDataFromJSONService")
	public ArchitectureResponse getDataFromJSONService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromJSONService"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("body", httpHeaders);
		String response = restTemplate.exchange(prop.getProperty("todoUrl"), HttpMethod.GET, entity, String.class)
				.getBody();
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		try {
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("Success");
			ToDoTO[] readValue = objectMapper.readValue(response, ToDoTO[].class);
			architectureResponse.setResponse(readValue);
		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.info(ArchitectureUtility.exitedFrom("getDataFromJSONService"));
		return architectureResponse;

	}
	@Cacheable("getDataFromXMLService")
	public ArchitectureResponse getDataFromXMLService() {
		logger.info(ArchitectureUtility.enteredInto("getDataFromXMLService"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.CONTENT_TYPE, "text/xml;charset=UTF-8");
			HttpEntity<String> entity = new HttpEntity<String>("body", httpHeaders);
			String response = restTemplate
					.exchange(prop.getProperty("datafromXML"), HttpMethod.GET, entity, String.class).getBody();
			JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
			Unmarshaller createUnmarshaller = jaxbContext.createUnmarshaller();
			Employees document = (Employees) createUnmarshaller.unmarshal(new StringReader(response));

			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("Success");
			architectureResponse.setResponse(document);
		} catch (Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.info(ArchitectureUtility.exitedFrom("getDataFromXMLService"));
		return architectureResponse;

	}
	
	@CacheEvict(value = "getDataFromXMLService", allEntries = true)
	public ArchitectureResponse removeCacheFromXMLService() {
		logger.info(ArchitectureUtility.enteredInto("removeCacheFromXMLService"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		try {
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("SUCCESS");
			architectureResponse.setResponse("XML Cache Cleared Successfully");
		}
		catch(Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
		}
		logger.info(ArchitectureUtility.exitedFrom("removeCacheFromXMLService"));
		return architectureResponse;
	}
	
	
	@CacheEvict(value = "getDataFromJSONService", allEntries = true)
	public ArchitectureResponse removeCacheFromJSONService() {
		logger.info(ArchitectureUtility.enteredInto("removeCacheFromJSONService"));
		ArchitectureResponse architectureResponse = new ArchitectureResponse();
		try {
			architectureResponse.setStatusCode(200);
			architectureResponse.setStatusMsg("SUCCESS");
			architectureResponse.setResponse("JSON Cache Cleared Successfully");
		}
		catch(Exception e) {
			architectureResponse.setStatusCode(400);
			architectureResponse.setStatusMsg(e.getMessage());
		}
		logger.info(ArchitectureUtility.exitedFrom("removeCacheFromJSONService"));
		return architectureResponse;
	}

}
