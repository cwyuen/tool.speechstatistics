package com.primecredit.tool.speechstatistics.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "NL_FILE")
public class FrequencyWordFile {
	@GraphId
	private Long id;
	private String key;
	private Date date;
	
	@Relationship(type = "NL_SOURCE")
	private List<NaturalLangSource> naturalLangSources = new ArrayList<>();
	
	
	public FrequencyWordFile() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<NaturalLangSource> getNaturalLangSources() {
		return naturalLangSources;
	}
	public void setNaturalLangSources(List<NaturalLangSource> naturalLangSources) {
		this.naturalLangSources = naturalLangSources;
	}

}
