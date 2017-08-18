package com.primecredit.tool.speechstatistics.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.primecredit.tool.speechstatistics.domain.FrequencyWordSource;

public interface FrequencyWordSourceDao  extends GraphRepository<FrequencyWordSource>{

	
	@Query("MATCH (n1:FQ_WORD) -[r1:FQ_SOURCE]- (n2:FQ_FILE) "
			+ "where id(n1) = {0} "
			+ "and id(n2) = {1} "
			+ "return r1")
	public List<FrequencyWordSource> findFrequencyWordSources(long wordId, long fileId);
}
