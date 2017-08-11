package com.primecredit.tool.speechstatistics.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "fq_word")
public class FrequencyWord {
	@GraphId
	private Long id;
	private String name;
	private int count = 0;

	@Relationship(type = "source", direction = Relationship.DIRECTION)
	private List<FrequencyWordRelationship> sources = new ArrayList<FrequencyWordRelationship>();

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public void addFrequencyWordRelationship(FrequencyWordRelationship vocabularyRelationship) {
		this.getSources().add(vocabularyRelationship);
	}

	public List<FrequencyWordRelationship> getSources() {
		return sources;
	}

	public void setSources(List<FrequencyWordRelationship> sources) {
		this.sources = sources;
	}
}
