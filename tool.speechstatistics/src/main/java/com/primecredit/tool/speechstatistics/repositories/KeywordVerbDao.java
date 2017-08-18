package com.primecredit.tool.speechstatistics.repositories;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.KeywordVerb;

public interface KeywordVerbDao  extends GraphRepository<KeywordVerb>{

	public List<KeywordVerb> findKeywordVerbByPath(@Param("path") String path); 
}
