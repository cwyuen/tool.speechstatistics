package com.primecredit.tool.speechstatistics.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primecredit.tool.common.wsobject.request.FrequencyWordRequest;
import com.primecredit.tool.common.wsobject.request.NaturalLangRequest;
import com.primecredit.tool.common.wsobject.response.FrequencyWordResponse;
import com.primecredit.tool.common.wsobject.response.NaturalLangResponse;
import com.primecredit.tool.speechstatistics.services.SpeechStatisticsService;

@RestController
@RequestMapping("/SpeechStatistics")
public class SpeechStatisticsController {
	private static Logger logger = LoggerFactory.getLogger(SpeechStatisticsController.class);

	@Autowired
	private SpeechStatisticsService speechStatisticsService;
	
	
	@RequestMapping(value = "/nlStatistics", method = RequestMethod.POST)
	public NaturalLangResponse naturalLangstatistics(@RequestBody NaturalLangRequest request) {

		logger.info("statistics source:" + request.getClientMachineId());
		logger.info("statistics source:" + request.getEntry().getName());
		
		boolean success = speechStatisticsService.saveNaturalLang(request.getEntry().getName(), request.getEntry().getType(), request.getSourceFile(), request.getLine());
		//boolean success = speechStatisticsService.statisticsFrequencyWord(request);

		NaturalLangResponse response = new NaturalLangResponse();
		response.setClientMachineId(request.getClientMachineId());
		response.setMillisecond(new Date().getTime());
		response.setSuccess(success);
		
		return response;

	}

	@RequestMapping(value = "/fqStatistics", method = RequestMethod.POST)
	public FrequencyWordResponse freqWordstatistics(@RequestBody FrequencyWordRequest request) {
		
		List<String> textList = speechStatisticsService.extractFrequencyWord(request.getText());
		for(String str : textList) {
			speechStatisticsService.saveFrequencyWord(str, request.getSourceFile(), request.getLine());
		}
		
		FrequencyWordResponse response = new FrequencyWordResponse();
		response.setClientMachineId(request.getClientMachineId());
		response.setMillisecond(new Date().getTime());
		response.setSuccess(true);
		
		return response;
	}
	
}
