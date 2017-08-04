package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.Vocabulary;

public interface VocabularyDao extends GraphRepository<Vocabulary>{

	public Vocabulary findByName(@Param("name") String name);
}
