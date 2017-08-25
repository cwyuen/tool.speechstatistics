package com.primecredit.tool.speechstatistics.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.Keyword;

public interface KeywordDao extends GraphRepository<Keyword>{

	public Keyword findByName(@Param("name") String name);
	
	@Query("match (:INITIALS {name:{0}}) -[:NEAR]- (i:INITIALS) with i "
			+ "match (:VOWEL {name:{1}}) -[:NEAR]- (v:VOWEL) with i,v "
			+ "match (k:KEYWORD) where k.initials = i.name and k.vowel = v.name "
			+ "return k")
	public List<Keyword> findByPinYin(String initials, String vowel);
	
	
}



