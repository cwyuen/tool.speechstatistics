package com.primecredit.tool.speechstatistics.domain;

import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "NL_SOURCE")
public class FrequencyWordSource {
	@GraphId
	private Long id;
	
	@StartNode 
	private NaturalLangWord naturalLangWord;
	
	@EndNode 
	private NaturalLangFile naturalLangFile;
	
	private List<Integer> lines ;
	
	public FrequencyWordSource() {
		
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


	public List<Integer> getLines() {
		return lines;
	}

	public void setLines(List<Integer> lines) {
		this.lines = lines;
	}

	public NaturalLangFile getNaturalLangFile() {
		return naturalLangFile;
	}

	public void setNaturalLangFile(NaturalLangFile naturalLangFile) {
		this.naturalLangFile = naturalLangFile;
	}



}
