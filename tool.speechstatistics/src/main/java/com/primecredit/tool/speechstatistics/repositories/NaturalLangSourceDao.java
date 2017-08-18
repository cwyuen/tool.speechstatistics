package com.primecredit.tool.speechstatistics.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.primecredit.tool.speechstatistics.domain.NaturalLangSource;

public interface NaturalLangSourceDao  extends GraphRepository<NaturalLangSource>{

	@Query("MATCH (n1:NL_WORD) -[r1:NL_SOURCE]- (n2:NL_FILE) "
			+ "where id(n1) = {0} "
			+ "and id(n2) = {1} "
			+ "return r1")
	public List<NaturalLangSource> findNaturalLangSources(long wordId, long fileId);
}
