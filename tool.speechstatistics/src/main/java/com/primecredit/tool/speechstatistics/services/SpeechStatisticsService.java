package com.primecredit.tool.speechstatistics.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primecredit.tool.common.util.ChineseUtils;
import com.primecredit.tool.speechstatistics.domain.FrequencyWord;
import com.primecredit.tool.speechstatistics.domain.FrequencyWordFile;
import com.primecredit.tool.speechstatistics.domain.FrequencyWordSource;
import com.primecredit.tool.speechstatistics.domain.Keyword;
import com.primecredit.tool.speechstatistics.domain.KeywordVerb;
import com.primecredit.tool.speechstatistics.domain.NaturalLangFile;
import com.primecredit.tool.speechstatistics.domain.NaturalLangSource;
import com.primecredit.tool.speechstatistics.domain.NaturalLangWord;
import com.primecredit.tool.speechstatistics.domain.PinYin;
import com.primecredit.tool.speechstatistics.repositories.FrequencyFileDao;
import com.primecredit.tool.speechstatistics.repositories.FrequencyWordDao;
import com.primecredit.tool.speechstatistics.repositories.FrequencyWordSourceDao;
import com.primecredit.tool.speechstatistics.repositories.KeywordDao;
import com.primecredit.tool.speechstatistics.repositories.KeywordVerbDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangFileDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangSourceDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangWordDao;

@Service
public class SpeechStatisticsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FrequencyFileDao frequencyFileDao;

	@Autowired
	private FrequencyWordDao frequencyWordDao;

	@Autowired
	private FrequencyWordSourceDao frequencyWordSourceDao;

	@Autowired
	private NaturalLangFileDao naturalLangFileDao;

	@Autowired
	private NaturalLangWordDao naturalLangWordDao;

	@Autowired
	private NaturalLangSourceDao naturalLangSourceDao;

	@Autowired
	private KeywordDao keywordDao;

	@Autowired
	private KeywordVerbDao keywordVerbDao;

	@Autowired
	private PinYinService pinYinService;

	@Transactional
	public boolean saveNaturalLang(String word, String type, String textFileName, int line) {
		boolean result = true;

		NaturalLangWord nlWord = naturalLangWordDao.findByName(word);
		if (nlWord == null) {
			nlWord = new NaturalLangWord();
			nlWord.setName(word);
			nlWord.setType(type);
			nlWord.setCount(0);
			naturalLangWordDao.save(nlWord);
		}

		NaturalLangFile statFile = naturalLangFileDao.findByKey(textFileName);
		if (statFile == null) {
			statFile = new NaturalLangFile();
			statFile.setKey(textFileName);
			statFile.setDate(new Date());
			naturalLangFileDao.save(statFile);
		}

		NaturalLangSource existSource = null;
		Iterable<NaturalLangSource> sources = naturalLangSourceDao.findNaturalLangSources(nlWord.getId(),
				statFile.getId());
		Iterator<NaturalLangSource> iter = sources.iterator();
		if (iter.hasNext()) {
			existSource = iter.next();
		}

		if (existSource == null) {
			existSource = new NaturalLangSource();
			existSource.setNaturalLangWord(nlWord);
			existSource.setNaturalLangFile(statFile);
			List<Integer> lines = new ArrayList<>();
			lines.add(line);
			existSource.setLines(lines);
		} else {
			List<Integer> lines = existSource.getLines();
			if (!lines.contains(line)) {
				lines.add(line);
			}
		}
		naturalLangSourceDao.save(existSource);

		nlWord.setCount(nlWord.getCount() + 1);
		naturalLangWordDao.save(nlWord);

		return result;
	}

	public List<String> extractFrequencyWord(String lineStr) {

		List<String> results = new ArrayList<>();

		String chineseLine = ChineseUtils.removeNonChinese(lineStr);

		String[] list = chineseLine.split("");

		int maxLength = list.length;

		for (int i = 0; i < list.length; i++) {
			String start = list[i];
			if (!start.equals("")) {

				// 3 Word Verb
				if (i + 2 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						results.add(sb.toString());
					}
				}

				// 4 Word Verb
				if (i + 3 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					String word4 = list[i + 3];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("") || !word4.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						sb.append(word4);
						results.add(sb.toString());
					}
				}

				// 5 Word Verb
				if (i + 4 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					String word4 = list[i + 3];
					String word5 = list[i + 4];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("") || !word4.equals("")
							|| !word5.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						sb.append(word4);
						sb.append(word5);
						results.add(sb.toString());
					}
				}

				// 6 Word Verb
				if (i + 5 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					String word4 = list[i + 3];
					String word5 = list[i + 4];
					String word6 = list[i + 5];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("") || !word4.equals("")
							|| !word5.equals("") || !word6.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						sb.append(word4);
						sb.append(word5);
						sb.append(word6);
						results.add(sb.toString());
					}
				}

			}

		}
		return results;
	}

	@Transactional
	public boolean saveFrequencyWord(String word, String sourceFileName, int line) {

		boolean result = true;
		FrequencyWord fqWord = frequencyWordDao.findByName(word);
		if (fqWord == null) {
			fqWord = new FrequencyWord();
			fqWord.setName(word);
			fqWord.setCount(0);
			fqWord = frequencyWordDao.save(fqWord);
		}

		FrequencyWordFile statFile = frequencyFileDao.findByKey(sourceFileName);
		if (statFile == null) {
			statFile = new FrequencyWordFile();
			statFile.setKey(sourceFileName);
			statFile.setDate(new Date());
			frequencyFileDao.save(statFile);
		}

		FrequencyWordSource existSource = frequencyWordSourceDao.findFrequencyWordSources(fqWord.getId(),
				statFile.getId());

		if (existSource == null) {
			existSource = new FrequencyWordSource();
			existSource.setFrequencyWord(fqWord);
			existSource.setFrequencyWordFile(statFile);
			List<Integer> lines = new ArrayList<>();
			lines.add(line);
			existSource.setLines(lines);
		} else {

			List<Integer> lines = existSource.getLines();
			if (!lines.contains(line)) {
				lines.add(line);
			}

		}
		frequencyWordSourceDao.save(existSource);

		fqWord.setCount(fqWord.getCount() + 1);
		frequencyWordDao.save(fqWord);

		return result;
	}

	public List<String> extractKeyword() {
		List<String> results = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("keyword.txt")))) {

			String line = null;
			while ((line = br.readLine()) != null) {
				results.add(line);
			}

		} catch (IOException e) {
			logger.error("IOException - SpeechStatisticsService.extractKeyword: {}", e.getMessage());
		}

		return results;

	}

	@Transactional
	public void saveKeyword(String keyword) {
		String[] verbs = keyword.split("");
		List<Keyword> keyList = new ArrayList<>();

		for (String verb : verbs) {
			Keyword kw = keywordDao.findByName(verb);
			if (kw == null) {
				PinYin py = pinYinService.cantonesePinYin(verb);
				kw = new Keyword();
				kw.setName(verb);
				kw.setInitials(py.getInitials());
				kw.setVowel(py.getVowel());
				kw.setTone(py.getTone());
				keywordDao.save(kw);
			}
			keyList.add(kw);
		}

		String path = keyword;
		List<KeywordVerb> vList = keywordVerbDao.findKeywordVerbByPath(path);
		if (vList.size() == 0) {

			for (int i = 0; i < keyList.size() - 1; i++) {
				Keyword start = keyList.get(i);
				Keyword end = keyList.get(i + 1);

				KeywordVerb verb = new KeywordVerb();
				verb.setKeyword1(start);
				verb.setKeyword2(end);
				verb.setPath(path);
				keywordVerbDao.save(verb);
			}

		}

	}

	@Transactional
	public Map<String,Double> matchingKeyword(String input) {
		Map<String, Double> resultMap = new LinkedHashMap<>();

		int length = input.length();

		Map<String, PinYin> pyMap = new HashMap<>();

		String[] verbs = input.split("");

		// 1) Get PinYin
		for (String verb : verbs) {
			PinYin py = pinYinService.cantonesePinYin(verb);
			pyMap.put(verb, py);
		}

		for (int i = 0; i < verbs.length; i++) {

			String verb = verbs[i];
			
			if(verb == null || verb.equals("")) {
				continue;
			}

			PinYin pinYin = pyMap.get(verb);
			List<Keyword> keyList = keywordDao.findByPinYin(pinYin.getInitials(), pinYin.getVowel());

			for (Keyword keyword : keyList) {
				Keyword kwCurr = keywordDao.findOne(keyword.getId());

				List<KeywordVerb> keywordVerbs = keywordVerbDao.findByStartNode(kwCurr.getId());

				for (KeywordVerb kv : keywordVerbs) {

					double fullMark = 100 * kv.getPath().length();
					double currMark = 0;
					
					String[] paths = kv.getPath().split("");
					
					int pos = i;
					for(int j=0; j<paths.length; j++){
						
						boolean match = false;
						
						for(int k=0; k<3; k++) {
							if(pos + k >= length) {
								break;
							}
							
							String next = verbs[pos+k];
							//100% Match
							if(next.equalsIgnoreCase(paths[j])) {
								currMark += 100 - ((k)*8);
								match = true;
								pos++;
								break;
							}
							
							//Check PinYin
							PinYin nextPinYin = pyMap.get(next);
							// Check if match Pin Yin
							List<Keyword> nextList = keywordDao.findByPinYin(nextPinYin.getInitials(), nextPinYin.getVowel());
							for (Keyword tmpNext : nextList) {
								if (tmpNext.getName().equals(paths[j])) {
												
									currMark += 100 - ((k+1)*20);
									
									//if match initials +10
									if(tmpNext.getInitials().equals(nextPinYin.getInitials())) {
										currMark += 10;
									}
									
									//if match vowel +10
									if(tmpNext.getVowel().equals(nextPinYin.getVowel())) {
										currMark += 10;
									}
									
									
									match = true;
									pos++;
									break;
								}
							}
							
							if(match) {
								break;
							}
						}
						
						
						if(!match) {
							break;
						}
					
					}
					
				
					double result = currMark / fullMark;
					if(resultMap.containsKey(kv.getPath())) {
						double curr = resultMap.get(kv.getPath());
						if(result > curr) {
							resultMap.put(kv.getPath(), result);
						}
					}else {
						if(result > 0.5) {
							resultMap.put(kv.getPath(), result);
						}
					}
					

				}
			}
		}
		return resultMap;
	}

}
