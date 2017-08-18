package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.FrequencyWordFile;

public interface FrequencyFileDao extends GraphRepository<FrequencyWordFile>{
	public FrequencyWordFile findByKey(@Param("key") String key);
}
