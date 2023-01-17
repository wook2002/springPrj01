package com.wook.prj01.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wook.prj01.web.token2.Token;

@Controller
public class RedisController {
	
	@Autowired
	private RedisService service;
	
	// Redis save
	@RequestMapping("/setToken")   
    public ResponseEntity<String> setToken(Token refreshToken){
    	return service.setToken(refreshToken);
    }
	
	@RequestMapping("/getToken")   
    public ResponseEntity<String> getToken(Token refreshToken){
    	return service.getToken(refreshToken);
    }


	
	

}
