package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.FrequencyWord;

public interface FrequencyWordDao extends GraphRepository<FrequencyWord>{

	public FrequencyWord findByName(@Param("name") String name);
}
