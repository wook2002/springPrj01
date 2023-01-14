package com.wook.prj01.web.token2;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<Token, String> {
	Optional<Token> findByKey(String key);
}
