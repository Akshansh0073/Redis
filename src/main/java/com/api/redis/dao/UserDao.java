package com.api.redis.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.api.redis.model.User;

@Service
public class UserDao {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String key="USER";
	
	
	public User save(User user) {
		
		redisTemplate.opsForHash().put(key, user.getUserId(), user);
		return user;
	}
	
	
	public User get(String userId) {
		
		 return (User) redisTemplate.opsForHash().get(key, userId);
	}
	
	
	public Map<Object,Object> findAll(){
		return redisTemplate.opsForHash().entries(key);
	}
	
	public void delete(String userId) {
		redisTemplate.opsForHash().delete(key, userId);
	}

}
