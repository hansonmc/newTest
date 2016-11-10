package com.jlc.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jlc.api.dao.UserMapper;
import com.jlc.api.dto.User;
import com.jlc.api.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	RedisTemplate<String, User> redisTemplate; 

	public RedisTemplate<String, User> getRedisTemplate() {  
        return redisTemplate;  
    }  
  
    public void setRedisTemplate(RedisTemplate<String, User> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    public void put(User user) {  
        redisTemplate.opsForHash().put(user.getObjectKey(), user.getKey(), user);  
    }  
  
    public void delete(User key) {  
        redisTemplate.opsForHash().delete(key.getObjectKey(), key.getKey());  
    }  
  
    public User get(User key) {  
        return (User) redisTemplate.opsForHash().get(key.getObjectKey(), key.getKey());  
    }  
	
    @Override
	public int addUser(User user)  throws Exception{
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}

	@Override
	public int deleteUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteUserById(id);
	}

	@Override
	public User getUserById(Integer id)  throws Exception{
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> selectUserList()  throws Exception{
		// TODO Auto-generated method stub
		return userMapper.selectUserList();
	}

	@Override
	public int updateUserById(User user)  throws Exception{
		// TODO Auto-generated method stub
		return userMapper.updateUserById(user);
	}
	
	
}
