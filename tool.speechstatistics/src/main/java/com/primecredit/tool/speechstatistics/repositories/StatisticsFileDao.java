package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.StatisticsFile;

public interface StatisticsFileDao extends GraphRepository<StatisticsFile>{

	public StatisticsFile findByKey(@Param("key") String key);
}
