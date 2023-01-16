package com.wook.prj01.web.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class TestCacheService {
	
	 @Cacheable(cacheNames="userCache")
	    public List<User> getUsers(){
	        List<User> users = new ArrayList<>();
	        users.add(new User("고객1", 21));
	        users.add(new User("고객2", 22));
	        users.add(new User("고객3", 23));
	        return users;
	    }

	    @Caching(evict = {
	            @CacheEvict(cacheNames="userCache", allEntries = true)
	        })
	    public void removeCacheUsers(){
	    }
	    
	    // https://riverblue.tistory.com/55
	    // https://zero-gravity.tistory.com/329
	    // https://dydtjr1128.github.io/redis/2019/04/03/Redis.html
	    
	    @Autowired
	    RedisTemplate<String, Object> redisTemplate;
	    

	    
		public void hiTest2() {
			System.out.println("testHi2");
//			val.set("testKey", "testValue"); // redis set
			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			String key = "newKey";
			
			// 1
	        vop.set("jdkSerial", key);
	        String result1 = (String) vop.get("jdkSerial");
	        System.out.println("result1 : " + result1);//jdk
	        
		}
		
		public void hiTest3() {
			System.out.println("testHi2");
//			System.out.println("됨됨:. ? : " + val.get("testKey"));
		}
}
