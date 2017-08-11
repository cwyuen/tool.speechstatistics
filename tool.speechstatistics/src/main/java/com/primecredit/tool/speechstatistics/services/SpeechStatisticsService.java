package com.primecredit.tool.speechstatistics.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primecredit.tool.common.util.ChineseUtils;
import com.primecredit.tool.speechstatistics.domain.FrequencyWord;
import com.primecredit.tool.speechstatistics.domain.FrequencyWordRelationship;
import com.primecredit.tool.speechstatistics.domain.NaturalLangRelationship;
import com.primecredit.tool.speechstatistics.domain.NaturalLangWord;
import com.primecredit.tool.speechstatistics.domain.StatisticsFile;
import com.primecredit.tool.speechstatistics.repositories.FrequencyWordDao;
import com.primecredit.tool.speechstatistics.repositories.NaturalLangWordDao;
import com.primecredit.tool.speechstatistics.repositories.StatisticsFileDao;

@Service
public class SpeechStatisticsService {

	@Autowired
	private StatisticsFileDao statisticsFileDao;

	@Autowired
	private FrequencyWordDao frequencyWordDao;
	
	@Autowired
	private NaturalLangWordDao naturalLangDao;
	
	public boolean saveNaturalLang(String word, String type, String textFileName, int line) {
		boolean result = true;
		
		NaturalLangWord nlWord = naturalLangDao.findByName(word);
		if(nlWord == null) {
			nlWord = new NaturalLangWord();
			nlWord.setName(word);
			nlWord.setType(type);
			nlWord.setCount(0);
			naturalLangDao.save(nlWord);
		}
		
		StatisticsFile statFile = statisticsFileDao.findByKey(textFileName);
		if(statFile == null) {
			statFile = new StatisticsFile();
			statFile.setKey(textFileName);
			statFile.setDate(new Date());
			statisticsFileDao.save(statFile);
		}
		
		nlWord.setCount(nlWord.getCount()+1);
		
		
		//Handle new Relationship
		if(!isExistNaturalLangRelationship(nlWord, statFile, line)) {
			NaturalLangRelationship nlr = new NaturalLangRelationship(nlWord, statFile,line);
			nlWord.addNaturalLangRelationship(nlr);
		}
		
		naturalLangDao.save(nlWord);
		
		return result;
	}
	
	private boolean isExistNaturalLangRelationship(NaturalLangWord nlWord, StatisticsFile statFile, int line) {
		boolean result = false;
		
		List<NaturalLangRelationship> sources = nlWord.getSources();
		if(sources != null) {
			for(NaturalLangRelationship re : sources) {
				if(re.getStatisticsFile().getId() == statFile.getId()) {
					if(re.getLine() == line) {
						return true;
					}
				}
			}
		}
		
		
		return result;
	}
	

	public boolean statisticsFrequencyWord(String sourceFileName, List<String> speechTexts) {

	
		StatisticsFile sf = statisticsFileDao.findByKey(sourceFileName);
		if (sf == null) {

			// Insert new statistics record
			sf = new StatisticsFile();
			sf.setKey(sourceFileName);
			sf.setDate(new Date());
			statisticsFileDao.save(sf);

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
		
		StatisticsFile statFile = statisticsFileDao.findByKey(sourceFileName);
		if(statFile == null) {
			statFile = new StatisticsFile();
			statFile.setKey(sourceFileName);
			statFile.setDate(new Date());
			statisticsFileDao.save(statFile);
		}
			
		fqWord.setCount(fqWord.getCount()+1);
		if(!isExistFrequencyWordRelationship(fqWord, statFile, line)) {
			FrequencyWordRelationship fqr = new FrequencyWordRelationship(fqWord, statFile,line);
			fqWord.addFrequencyWordRelationship(fqr);
			frequencyWordDao.save(fqWord);
		}
		
		frequencyWordDao.save(fqWord);
	
	}

	private boolean isExistFrequencyWordRelationship(FrequencyWord fqWord, StatisticsFile statFile, int line) {
		boolean result = false;
		
		List<FrequencyWordRelationship> sources = fqWord.getSources();
		if(sources != null) {
			for(FrequencyWordRelationship re : sources) {
				if(re.getStatisticsFile().getId() == statFile.getId()) {
					if(re.getLine() == line) {
						return true;
					}
				}
			}
		}
		
		
		return result;
	}
}
