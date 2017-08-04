package com.primecredit.tool.speechstatistics.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Vocabulary {
	@GraphId
	private Long id;
	private String name;
	private int count = 0;

	@Relationship(type = "contain", direction = Relationship.DIRECTION)
	private List<VocabularyRelationship> contains = new ArrayList<VocabularyRelationship>();

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

	public List<VocabularyRelationship> getContains() {
		return contains;
	}

	public void setContains(List<VocabularyRelationship> contains) {
		this.contains = contains;
	}

	public void addVocabularyRelationship(VocabularyRelationship vocabularyRelationship) {
		this.getContains().add(vocabularyRelationship);
	}
}
