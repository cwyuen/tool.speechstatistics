package com.primecredit.tool.speechstatistics.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FROM")
public class VocabularyRelationship {
	
	@GraphId
	private Long id;
	
	@StartNode
	Vocabulary vocabularyNode;
	
	@EndNode
	Sentence sentenceNode;
	
	public VocabularyRelationship() {
		
	}
	
	public VocabularyRelationship(Vocabulary vocabularyNode, Sentence sentenceNode) {
		this.vocabularyNode = vocabularyNode;
		this.sentenceNode = sentenceNode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vocabulary getVocabularyNode() {
		return vocabularyNode;
	}

	public void setVocabularyNode(Vocabulary vocabularyNode) {
		this.vocabularyNode = vocabularyNode;
	}

	public Sentence getSentenceNode() {
		return sentenceNode;
	}

	public void setSentenceNode(Sentence sentenceNode) {
		this.sentenceNode = sentenceNode;
	}
}
