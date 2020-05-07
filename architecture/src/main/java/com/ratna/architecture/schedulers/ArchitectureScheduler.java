package com.ratna.architecture.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArchitectureScheduler {

	@Scheduled(cron = "0 0/2 * * * ?")
	public void architectureHealthCheck() {
		System.out.println("Architecture is in running state...");

	}

}
