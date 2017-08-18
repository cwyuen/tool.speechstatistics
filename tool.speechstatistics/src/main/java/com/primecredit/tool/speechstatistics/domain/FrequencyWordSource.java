package com.primecredit.tool.speechstatistics.domain;

import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FQ_SOURCE")
public class FrequencyWordSource {
	@GraphId
	private Long id;
	
	@StartNode 
	private FrequencyWord frequencyWord;
	
	@EndNode 
	private FrequencyWordFile frequencyWordFile;
	
	private List<Integer> lines ;
	
	public FrequencyWordSource() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FrequencyWord getFrequencyWord() {
		return frequencyWord;
	}

	public void setFrequencyWord(FrequencyWord frequencyWord) {
		this.frequencyWord = frequencyWord;
	}

	public FrequencyWordFile getFrequencyWordFile() {
		return frequencyWordFile;
	}

	public void setFrequencyWordFile(FrequencyWordFile frequencyWordFile) {
		this.frequencyWordFile = frequencyWordFile;
	}

	public List<Integer> getLines() {
		return lines;
	}

	public void setLines(List<Integer> lines) {
		this.lines = lines;
	}




}
