package com.primecredit.tool.speechstatistics.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.primecredit.tool.speechstatistics.domain.NaturalLangWord;

public interface NaturalLangWordDao extends GraphRepository<NaturalLangWord>{


	public NaturalLangWord findByName(@Param("name") String name);

}
