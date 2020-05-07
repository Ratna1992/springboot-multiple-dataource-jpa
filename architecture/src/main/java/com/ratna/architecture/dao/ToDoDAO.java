package com.ratna.architecture.dao;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.transferobjects.Document;
import com.ratna.architecture.transferobjects.Employees;
import com.ratna.architecture.transferobjects.ToDoTO;

@Repository
public class ToDoDAO {
	@Autowired
	Environment prop;
	@Autowired
	RestTemplate restTemplate;

	public ArchitectureResponse getDataFromJSONService() {
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
		}
		return architectureResponse;

	}

	public ArchitectureResponse getDataFromXMLService() {
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
		}
		return architectureResponse;

	}

}
