package com.wook.prj01.web.redis;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wook.prj01.web.member.dto.Member;
import com.wook.prj01.web.token2.Token;
import com.wook.prj01.web.token2.TokenProvider;

// https://eblo.tistory.com/85
@RestController
public class TestController {

	@Autowired
    private TestCacheService testCacheService;
    
//	캐시 
    @RequestMapping("/test")
    public List<User> getUsers() {//Welcome page, non-rest
        return testCacheService.getUsers();
    }

    @RequestMapping("/test-refresh")
    public List<User> refreshUsers() {//Welcome page, non-rest
        testCacheService.removeCacheUsers();
        return testCacheService.getUsers();
    }
//    여기까지
    @RequestMapping("/hiTest")
    public String hiTest(HttpSession session) {//Welcome page, non-rest
    	if(session.getAttribute("test") == null) {
    		session.setAttribute("test", "hi");
    	}else {
	    	System.out.println("test임 : " + session.getAttribute("test"));
    	}
        return "";
    }
    
    @RequestMapping("/hiTest2")
	public String hiTest2() {
		 testCacheService.hiTest2();
		 return "1";
	}
    
    @RequestMapping("/hiTest3")
   	public String hiTest3() {
   		 testCacheService.hiTest3();
   		return "2";
   	}
    
    @Autowired    
    private RedisTemplate<String, String> redisTemplate;     
    
    @RequestMapping("/redisTest")    
    public ResponseEntity<?> addRedisKey() {        
    	ValueOperations<String, String> vop = redisTemplate.opsForValue();        
        vop.set("yellow", "banana");        
        vop.set("red", "apple");        
        vop.set("green", "watermelon");        
        return new ResponseEntity<>(HttpStatus.CREATED);    
    }   
    @RequestMapping("/redisTest/{key}")    
    public ResponseEntity<?> getRedisKey(@PathVariable String key) {        
    	ValueOperations<String, String> vop = redisTemplate.opsForValue();        
        String value = vop.get(key);        
        return new ResponseEntity<>(value, HttpStatus.OK);    
    }
    
    
    
    @Autowired
	TokenProvider tokenProvider; 
    
    @RequestMapping("/tokenTest")
   	public String tokenTest(HttpSession session) {
    	System.out.println("tokenTest");
    	
    	String id = "id1";
		String pw = "1234";
		
		Member member = new Member();
		member.setUser_id(id);
		member.setPassword(pw);
		
		Token accessToken = tokenProvider.createAccessToken(member);
		Token refreshToken = tokenProvider.createRefreshToken(member);
		
		System.out.println("accessToken : " + accessToken);
		System.out.println("refreshToken : " + refreshToken);
		
   		return "2";
   	}

}
