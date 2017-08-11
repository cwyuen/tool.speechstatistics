package com.primecredit.tool.speechstatistics.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FROM")
public class FrequencyWordRelationship {
	
	@GraphId
	private Long id;
	
	@StartNode
	private FrequencyWord vocabularyNode;
	
	@EndNode
	private StatisticsFile statisticsFile;
	
	private int line;
	
	public FrequencyWordRelationship() {
		
	}
	
	public FrequencyWordRelationship(FrequencyWord vocabularyNode, StatisticsFile statisticsFile, int line) {
		this.vocabularyNode = vocabularyNode;
		this.statisticsFile = statisticsFile;
		this.line = line;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FrequencyWord getVocabularyNode() {
		return vocabularyNode;
	}

	public void setVocabularyNode(FrequencyWord vocabularyNode) {
		this.vocabularyNode = vocabularyNode;
	}

	

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public StatisticsFile getStatisticsFile() {
		return statisticsFile;
	}

	public void setStatisticsFile(StatisticsFile statisticsFile) {
		this.statisticsFile = statisticsFile;
	}
}
