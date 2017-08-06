package com.primecredit.tool.speechstatistics.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primecredit.tool.common.wsobject.request.SpeechStatisticsRequest;
import com.primecredit.tool.common.wsobject.response.SpeechStatisticsResponse;
import com.primecredit.tool.speechstatistics.services.SpeechStatisticsService;

@RestController
@RequestMapping("/SpeechStatistics")
public class SpeechStatisticsController {
	private static Logger logger = LoggerFactory.getLogger(SpeechStatisticsController.class);

	@Autowired
	private SpeechStatisticsService speechStatisticsService;

	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	public SpeechStatisticsResponse statistics(@RequestBody SpeechStatisticsRequest request) {

		logger.info("statistics source:" + request.getSourceFileName());
		
		
		boolean success = speechStatisticsService.statistics(request);

		SpeechStatisticsResponse response = new SpeechStatisticsResponse();
		response.setClientMachineId(request.getClientMachineId());
		response.setMillisecond(new Date().getTime());
		response.setSuccess(success);
		
		return response;

	}

}
