package com.primecredit.tool.speechstatistics.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "KW_VERB")
public class KeywordVerb {

	@GraphId
	private Long id;
	
	@StartNode 
	private Keyword keyword1;
	
	@EndNode 
	private Keyword keyword2;
	
	private String path;
	
	
	public KeywordVerb() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Keyword getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(Keyword keyword1) {
		this.keyword1 = keyword1;
	}

	public Keyword getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(Keyword keyword2) {
		this.keyword2 = keyword2;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
