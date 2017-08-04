package com.primecredit.tool.speechstatistics.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primecredit.tool.common.util.ChineseUtil;
import com.primecredit.tool.common.wsobject.request.SpeechStatisticsRequest;
import com.primecredit.tool.speechstatistics.domain.Sentence;
import com.primecredit.tool.speechstatistics.domain.StatisticsFile;
import com.primecredit.tool.speechstatistics.domain.Vocabulary;
import com.primecredit.tool.speechstatistics.domain.VocabularyRelationship;
import com.primecredit.tool.speechstatistics.repositories.SentenceDao;
import com.primecredit.tool.speechstatistics.repositories.StatisticsFileDao;
import com.primecredit.tool.speechstatistics.repositories.VocabularyDao;

@Service
public class SpeechStatisticsService {

	@Autowired
	private StatisticsFileDao statisticsFileDao;

	@Autowired
	private VocabularyDao vocabularyDao;
	
	@Autowired
	private SentenceDao sentenceDao;

	public boolean statistics(SpeechStatisticsRequest request) {

		StringBuilder sbFileKey = new StringBuilder();
		sbFileKey.append(request.getClientMachineId());
		sbFileKey.append("_");
		sbFileKey.append(request.getSourceFileName());

		StatisticsFile sf = statisticsFileDao.findByKey(sbFileKey.toString());
		if (sf == null) {

			// Insert new statistics record
			sf = new StatisticsFile();
			sf.setKey(sbFileKey.toString());
			sf.setDate(new Date());
			statisticsFileDao.save(sf);

			for (String str : request.getSpeechTexts()) {
				Sentence sentence = new Sentence();
				sentence.setValue(str);
				sentence = sentenceDao.save(sentence);
				
				statisticsLine(sentence, str);
			}
		}

		return true;
	}

	private void statisticsLine(Sentence sentence,  String line) {

		String chineseLine = ChineseUtil.removeNonChinese(line);

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
						updateWordStatistics(sentence,sb.toString());
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
						updateWordStatistics(sentence,sb.toString());
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
						updateWordStatistics(sentence,sb.toString());
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
						updateWordStatistics(sentence,sb.toString());
					}
				}

			}

		}
	}

	private void updateWordStatistics(Sentence sentence, String word) {
		Vocabulary vocabulary = vocabularyDao.findByName(word);
		if (vocabulary == null) {
			vocabulary = new Vocabulary();
			vocabulary.setName(word);
			vocabulary.setCount(1);
			vocabulary = vocabularyDao.save(vocabulary);
			
			VocabularyRelationship vocabularyRelationship = new VocabularyRelationship();
			vocabularyRelationship.setVocabularyNode(vocabulary);
			vocabularyRelationship.setSentenceNode(sentence);
			vocabulary.addVocabularyRelationship(vocabularyRelationship);
			
			vocabulary = vocabularyDao.save(vocabulary);
			
		} else {
			vocabulary.setCount(vocabulary.getCount() + 1);
			VocabularyRelationship vocabularyRelationship = new VocabularyRelationship();
			vocabularyRelationship.setVocabularyNode(vocabulary);
			vocabularyRelationship.setSentenceNode(sentence);
			vocabulary = vocabularyDao.save(vocabulary);
		}

		
	}
}
