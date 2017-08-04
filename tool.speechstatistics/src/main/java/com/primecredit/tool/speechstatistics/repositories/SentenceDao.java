package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.primecredit.tool.speechstatistics.domain.Sentence;

public interface SentenceDao extends GraphRepository<Sentence> {

}
