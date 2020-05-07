package com.ratna.architecture.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArchitectureScheduler {
	private Logger logger = LoggerFactory.getLogger(ArchitectureScheduler.class);
	@Scheduled(cron = "0 0/2 * * * ?")
	public void architectureHealthCheck() {
		logger.debug("Architecture is in running state...");

	}

}
