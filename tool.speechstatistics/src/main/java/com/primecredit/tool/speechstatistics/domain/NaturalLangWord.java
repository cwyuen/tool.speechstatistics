package com.primecredit.tool.speechstatistics.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "nl_word")
public class NaturalLangWord {
	@GraphId
	private Long id;
	private String name;
	private String type;
	private int count = 0;
	
	@Relationship(type = "source", direction = Relationship.DIRECTION)
	private List<NaturalLangRelationship> sources = new ArrayList<NaturalLangRelationship>();
	
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
	public List<NaturalLangRelationship> getSources() {
		return sources;
	}
	public void setSources(List<NaturalLangRelationship> sources) {
		this.sources = sources;
	}
	
	public void addNaturalLangRelationship(NaturalLangRelationship naturalLangRelationship) {
		this.getSources().add(naturalLangRelationship);
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
