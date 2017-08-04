package com.primecredit.tool.speechstatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpeechStatisticsWsApplication {

	private static Logger logger = LoggerFactory.getLogger(SpeechStatisticsWsApplication.class);
	
	public static void main(String[] args) {
		logger.debug("SpeechWsApplication - Start");
		SpringApplication.run(SpeechStatisticsWsApplication.class, args);
	}
}
