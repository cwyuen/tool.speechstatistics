package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.NaturalLangFile;

public interface NaturalLangFileDao extends GraphRepository<NaturalLangFile> {

	public NaturalLangFile findByKey(@Param("key") String key);
}
