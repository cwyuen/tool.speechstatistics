package com.primecredit.tool.speechstatistics.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "KEYWORD")
public class Keyword{
	@GraphId
	private Long id;
	private String name;
	private String initials;
	private String vowel;
	private String tone;
	
	@Relationship(type = "KW_VERB")
	private List<KeywordVerb> keywordVerbs = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getVowel() {
		return vowel;
	}
	public void setVowel(String vowel) {
		this.vowel = vowel;
	}
	public String getTone() {
		return tone;
	}
	public void setTone(String tone) {
		this.tone = tone;
	}
	public List<KeywordVerb> getKeywordVerbs() {
		return keywordVerbs;
	}
	public void setKeywordVerbs(List<KeywordVerb> keywordVerbs) {
		this.keywordVerbs = keywordVerbs;
	}
}
