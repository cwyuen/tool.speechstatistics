package com.primecredit.tool.speechstatistics.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "NL_WORD")
public class NaturalLangWord {

	@GraphId
	private Long id;
	private String name;
	private String type;
	private int count = 0;

	@Relationship(type = "NL_SOURCE")
	private List<NaturalLangSource> naturalLangSources = new ArrayList<>();
	
	public NaturalLangWord() {
	}

	public NaturalLangWord(String name, String type) {
		this.name = name;
		this.type = type;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<NaturalLangSource> getNaturalLangSources() {
		return naturalLangSources;
	}

	public void setNaturalLangSources(List<NaturalLangSource> naturalLangSources) {
		this.naturalLangSources = naturalLangSources;
	}

	
}
