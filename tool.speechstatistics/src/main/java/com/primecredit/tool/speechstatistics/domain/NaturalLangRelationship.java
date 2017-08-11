package com.primecredit.tool.speechstatistics.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "SOURCE")
public class NaturalLangRelationship {
	
	@GraphId
	private Long id;
	
	@StartNode
	private NaturalLangWord naturalLangWord;
	
	@EndNode
	private StatisticsFile statisticsFile;
	
	private int line;
	
	
	public NaturalLangRelationship() {
		
	}
	
	public NaturalLangRelationship(NaturalLangWord naturalLangWord, StatisticsFile statisticsFile, int line) {
		this.naturalLangWord = naturalLangWord;
		this.statisticsFile = statisticsFile;
		this.line = line;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public NaturalLangWord getNaturalLangWord() {
		return naturalLangWord;
	}

	public void setNaturalLangWord(NaturalLangWord naturalLangWord) {
		this.naturalLangWord = naturalLangWord;
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
