package com.primecredit.tool.speechstatistics;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.primecredit.tool.speechstatistics.services.SpeechStatisticsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeechStatisticsTestApplication {

	private static Logger logger = LoggerFactory.getLogger(SpeechStatisticsTestApplication.class);
	
	@Autowired
	private SpeechStatisticsService speechStatisticsService;

	
	//@Test
	public void testNaturalLang() {
		logger.debug("SpeechStatisticsTestApplication.testNaturalLang - Start");
		boolean result = true;

		try {
			result = result && speechStatisticsService.saveNaturalLang("Apple", "ORGANIZATION", "ORGANIZATION2.txt", 1);
			result = result && speechStatisticsService.saveNaturalLang("Apple", "ORGANIZATION", "ORGANIZATION2.txt", 5);
			//result = result && speechStatisticsService.saveNaturalLang("Apple", "ORGANIZATION", "ORGANIZATION.txt", 15);
			//result = result && speechStatisticsService.saveNaturalLang("Orange", "ORGANIZATION", "ORGANIZATION.txt", 1);
			//result = result && speechStatisticsService.saveNaturalLang("Orange", "ORGANIZATION", "ORGANIZATION.txt", 4);
		} catch (Exception e) {
			logger.error("Exception - SpeechStatisticsTestApplication.testNaturalLang: " + e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	//@Test
	public void testFrequencyWord() {
		logger.debug("SpeechStatisticsTestApplication.testNaturalLang - Start");
		boolean result = true;
		List<String> speechTexts = new ArrayList<>();
		speechTexts.add("將軍澳");
		speechTexts.add("將軍澳");
		speechTexts.add("將軍澳");
		//speechTexts.add("你都唔夠還是一黃婉婷小姐我哋今晚上覆返你個出個情況以及安排30日使唔使宜家方唔方便");
		//speechTexts.add("ok咁我先啦六宮粉馬會係5990");
		//speechTexts.add("你可以幫我最主要啦你兩個安排講呀同埋要嘅取消信用卡嘅情況呢其實我黃小姐就只需要帶張匯豐信用卡啦咁以及就係恆生嗰張信用卡咁我哋會幫你抄低返個號碼恆生就會幫你收皮我放番號碼以及簽番個");
		//speechTexts.add("萬蚊嘅匯豐方面嘅話呢我哋就會即時呢喺你面前就幫你剪咗時間佢啦咁樣剪咗張相睇佢咁以及就會寫返封授權信我哋會幫你郵寄生嗰張信用卡去匯豐銀行嗰邊去處");
		//speechTexts.add("提你應該要找番嗰個數學啦打個比如果你信用卡裏面有分期嘅話呢你都要通知佢哋啦因為你嗰張信用卡會取消同埋你會講啦今日咁所以呢你要同通知番嗰邊如果你找埋個分期過數大概幾多錢都咁要去幫你去");
		//speechTexts.add("我見得到信貸報告裏面呢兩個數加拿大概係20萬寧1102蚊嘅咁如果你購買嗰個80蚊嗰個呀延期還款利息你所得嘅");
		//speechTexts.add("我將軍澳嗰個呀你嗰個成員編號嗰個資料理其他人而家呢度成員資料我睇到去如果你食完之後睇到就得㗎喇");
		//speechTexts.add("落馬地金基金表現九龍灣去錦田市場投資組合");
		try {
			//result = result && speechStatisticsService.statisticsFrequencyWord("Fff0001.txt", speechTexts);
			//result = result && speechStatisticsService.statisticsFrequencyWord("Fff0002.txt", speechTexts);
			
			result = result && speechStatisticsService.saveFrequencyWord("Apple", "ORGANIZATION2.txt", 1);
			result = result && speechStatisticsService.saveFrequencyWord("Apple", "ORGANIZATION2.txt", 3);
			result = result && speechStatisticsService.saveFrequencyWord("Apple", "ORGANIZATION2.txt", 5);
		} catch (Exception e) {
			logger.error("Exception - SpeechStatisticsTestApplication.testNaturalLang: " + e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	//@Test
	public void testImportKeyword() {
		logger.debug("SpeechStatisticsTestApplication.testImportKeyword - Start");
		boolean result = true;
		List<String> keywords = speechStatisticsService.extractKeyword();
		for(String keyword : keywords) {
			speechStatisticsService.saveKeyword(keyword);
		}
		
		
		logger.debug("SpeechStatisticsTestApplication.testImportKeyword - End");
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testSpeechMatch() {
		logger.debug("SpeechStatisticsTestApplication.testSpeechMatch - Start");
		String input = "齋怒重早太梡";
		
		speechStatisticsService.matchingKeyword(input);
		logger.debug("SpeechStatisticsTestApplication.testSpeechMatch - End");
	}
	
	
}


