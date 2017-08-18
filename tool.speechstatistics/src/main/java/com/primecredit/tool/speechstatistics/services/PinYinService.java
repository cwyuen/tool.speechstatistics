package com.primecredit.tool.speechstatistics.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.primecredit.tool.common.util.ChineseUtils;
import com.primecredit.tool.speechstatistics.domain.PinYin;

@Service
public class PinYinService {
	private static String CANTONESE_SERVICE_URL = "http://humanum.arts.cuhk.edu.hk/Lexis/lexi-can/";
	private static final Logger logger = LoggerFactory.getLogger(PinYinService.class);
	private static final String KEY1 = "<td nowrap align=center><font color=red size=+1>";
	private static final String KEY2 = "</font><font color=green size=+1>";
	private static final String KEY3 = "</font><font color=blue size=+1>";
	private static final String KEY4 = "</font></td>";



	public PinYin cantonesePinYin(String input) {
		PinYin word = new PinYin();
		word.setName(input);

		List<String> html = new ArrayList<String>();

		String big5Str = ChineseUtils.convertToUrlBig5(input);
		String urlStr = CANTONESE_SERVICE_URL + "search.php?q=" + big5Str;

		URL url = null;

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException - PinYinService.cantonesePinYin: {}" +e.getMessage());
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
			String htmlLine;
			while ((htmlLine = br.readLine()) != null) {
				html.add(htmlLine);
			}
		} catch (IOException e) {
			logger.error("IOException - PinYinService.cantonesePinYin: {}" +e.getMessage());
		}

		for (String line : html) {
			line = line.trim();
			if (line.startsWith(KEY1)) {
				int start = line.indexOf(KEY1);
				start = start + KEY1.length();
				int end = line.indexOf(KEY2);
				String initials = line.substring(start, end);

				start = line.indexOf(KEY2);
				start = start + KEY2.length();
				end = line.indexOf(KEY3);
				String vowel = line.substring(start, end);

				start = line.indexOf(KEY3);
				start = start + KEY3.length();
				end = line.indexOf(KEY4);
				String tone = line.substring(start, end);

				word.setInitials(initials);
				word.setVowel(vowel);
				word.setTone(tone);
				break;
			}

		}

		return word;
	}
}
