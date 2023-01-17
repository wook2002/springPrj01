package com.wook.prj01.web.redis;

import org.springframework.http.ResponseEntity;

import com.wook.prj01.web.token2.Token;

public interface RedisService {

	ResponseEntity<String> setToken(Token refreshToken);

	ResponseEntity<String> getToken(Token refreshToken);

}
