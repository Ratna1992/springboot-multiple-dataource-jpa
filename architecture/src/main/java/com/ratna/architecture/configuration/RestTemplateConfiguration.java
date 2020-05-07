package com.ratna.architecture.configuration;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.ratna.architecture.utility.ArchitectureUtility;

@Configuration
public class RestTemplateConfiguration {

	private Logger logger = LoggerFactory.getLogger(RestTemplateConfiguration.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		logger.info(ArchitectureUtility.enteredInto("restTemplate"));
		logger.info(
				"This method is used to trust the certificates of RestTemplateTemplate and can able to access any server to get response");
		TrustStrategy accepTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, accepTrustStrategy).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplateBuilder.configure(restTemplate);
		logger.info(ArchitectureUtility.exitedFrom("restTemplate"));
		return restTemplateBuilder.build();
	}

}
