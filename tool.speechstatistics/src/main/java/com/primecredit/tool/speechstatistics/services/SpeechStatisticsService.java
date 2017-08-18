package com.primecredit.tool.speechstatistics.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primecredit.tool.common.util.ChineseUtils;
import com.primecredit.tool.speechstatistics.domain.FrequencyWord;
import com.primecredit.tool.speechstatistics.domain.FrequencyWordFile;
import com.primecredit.tool.speechstatistics.domain.FrequencyWordSource;
import com.primecredit.tool.speechstatistics.domain.NaturalLangFile;
import com.primecredit.tool.speechstatistics.domain.NaturalLangSource;
import com.primecredit.tool.speechstatistics.domain.NaturalLangWord;
import com.primecredit.tool.speechstatistics.repositories.FrequencyFileDao;
import com.primecredit.tool.speechstatistics.repositories.FrequencyWordDao;
import com.primecredit.tool.speechstatistics.repositories.FrequencyWordSourceDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangFileDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangSourceDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangWordDao;

@Service
public class SpeechStatisticsService {

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
	
	
	
	@Transactional
	public boolean saveNaturalLang(String word, String type, String textFileName, int line) {
		boolean result = true;
		
		NaturalLangWord nlWord = naturalLangWordDao.findByName(word);
		if(nlWord == null) {
			nlWord = new NaturalLangWord();
			nlWord.setName(word);
			nlWord.setType(type);
			nlWord.setCount(0);
			naturalLangWordDao.save(nlWord);
		}
		
		NaturalLangFile statFile = naturalLangFileDao.findByKey(textFileName);
		if(statFile == null) {
			statFile = new NaturalLangFile();
			statFile.setKey(textFileName);
			statFile.setDate(new Date());
			naturalLangFileDao.save(statFile);
		}
		
		NaturalLangSource existSource= null;
		Iterable<NaturalLangSource> sources = naturalLangSourceDao.findNaturalLangSources(nlWord.getId(), statFile.getId());
		Iterator<NaturalLangSource> iter = sources.iterator();
		if(iter.hasNext()) {
			existSource = iter.next();
		}
		
		if(existSource == null) {
			existSource = new NaturalLangSource();
			existSource.setNaturalLangWord(nlWord);
			existSource.setNaturalLangFile(statFile);
			List<Integer> lines = new ArrayList<>();
			lines.add(line);
			existSource.setLines(lines);
		}else {
			List<Integer> lines = existSource.getLines();
			if(!lines.contains(line)) {
				lines.add(line);
			}
		}
		naturalLangSourceDao.save(existSource);
		
		
		nlWord.setCount(nlWord.getCount()+1);
		naturalLangWordDao.save(nlWord);
		
		return result;
	}
	
	
	

	public boolean statisticsFrequencyWord(String sourceFileName, List<String> speechTexts) {

	
		FrequencyWordFile sf = frequencyFileDao.findByKey(sourceFileName);
		if (sf == null) {

			// Insert new statistics record
			sf = new FrequencyWordFile();
			sf.setKey(sourceFileName);
			sf.setDate(new Date());
			frequencyFileDao.save(sf);

			int line = 0;
			for (String lineStr : speechTexts) {
				line++;
				statisticsFrequencyWordLine(sourceFileName, line, lineStr);
			}
		}

		return true;
	}

	private void statisticsFrequencyWordLine(String sourceFileName,int lineNum, String lineStr) {

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
						this.saveFrequencyWord(sb.toString(), sourceFileName, lineNum);
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
						this.saveFrequencyWord(sb.toString(), sourceFileName, lineNum);
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
						this.saveFrequencyWord(sb.toString(), sourceFileName, lineNum);
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
						this.saveFrequencyWord(sb.toString(), sourceFileName, lineNum);
					}
				}

			}

		}
	}

	private void saveFrequencyWord(String word, String sourceFileName, int line) {
		FrequencyWord fqWord = frequencyWordDao.findByName(word);
		if (fqWord == null) {
			fqWord = new FrequencyWord();
			fqWord.setName(word);
			fqWord.setCount(0);
			fqWord = frequencyWordDao.save(fqWord);
		}
		
		FrequencyWordFile statFile = frequencyFileDao.findByKey(sourceFileName);
		if(statFile == null) {
			statFile = new FrequencyWordFile();
			statFile.setKey(sourceFileName);
			statFile.setDate(new Date());
			frequencyFileDao.save(statFile);
		}
		
		FrequencyWordSource existSource= null;
		Iterable<FrequencyWordSource> sources = frequencyWordSourceDao.findFrequencyWordSources(fqWord.getId(), statFile.getId());
		//Iterable<FrequencyWordSource> sources = frequencyWordSourceDao.findAll();
		Iterator<FrequencyWordSource> iter = sources.iterator();
		if(iter.hasNext()) {
			existSource = iter.next();
		}
		
		if(existSource == null) {
			existSource = new FrequencyWordSource();
			existSource.setFrequencyWord(fqWord);
			existSource.setFrequencyWordFile(statFile);
			List<Integer> lines = new ArrayList<>();
			lines.add(line);
			existSource.setLines(lines);
		}else {
			List<Integer> lines = existSource.getLines();
			if(!lines.contains(line)) {
				lines.add(line);
			}
		}
		frequencyWordSourceDao.save(existSource);
			
		fqWord.setCount(fqWord.getCount()+1);		
		frequencyWordDao.save(fqWord);
	
	}

	
	
}
