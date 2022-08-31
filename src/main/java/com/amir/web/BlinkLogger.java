package com.amir.web;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlinkLogger {

	Logger log = LoggerFactory.getLogger(BlinkLogger.class);
	
	@Scheduled(fixedDelay = 1000)
	public void blink() {
	log.info("Blinking application: {} ",LocalDateTime.now());	
		
	}
}
