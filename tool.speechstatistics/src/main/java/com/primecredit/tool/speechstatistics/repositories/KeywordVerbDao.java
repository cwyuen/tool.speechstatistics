package com.primecredit.tool.speechstatistics.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.Keyword;
import com.primecredit.tool.speechstatistics.domain.KeywordVerb;

public interface KeywordVerbDao extends GraphRepository<KeywordVerb> {

	public List<KeywordVerb> findKeywordVerbByPath(@Param("path") String path);

	@Query("MATCH p=(n:KEYWORD) -[r:KW_VERB]-> () where r.path={path} return n")
	public List<Keyword> findKeywordByPath(@Param("path") String path);

	@Query("MATCH p=(k:KEYWORD)-[r:KW_VERB]->(:KEYWORD) where id(k)= {0} return r ")
	public List<KeywordVerb> findByStartNode(long id);

	

	@Query("match p=(k1:KEYWORD) -[r:KW_VERB]-> (k2:KEYWORD) "
			+ "where id(k1) = {0} "
			+ "and k2.name = {1} "
			+ "and r.path = {2} "
			+ "return count(p)")
	public int getCountForMatching(long startNodeId, String endNodeName,  String path);
}
