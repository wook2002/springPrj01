package com.wook.prj01.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wook.prj01.web.token2.Token;

@Service
public class RedisServiceImpl implements RedisService {

	 @Autowired    
    private RedisTemplate<String, String> redisTemplate;
	 
	
	@Override
	public ResponseEntity<String> setToken(Token refreshToken) {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		vop.set("key", refreshToken.getKey());        
        vop.set("value", refreshToken.getValue());        
        return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> getToken(Token refreshToken) {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		String value = vop.get("value"); 
		return new ResponseEntity<>(value, HttpStatus.OK);
	}

}
