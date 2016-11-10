package com.jlc.api.service;

import java.util.List;

import com.jlc.api.dto.User;


public interface UserService {
	/**
	 * 增加用户
	 * 
	 * */
	public int addUser(User user) throws Exception;
	/**
	 * 修改用户
	 * 
	 * */
	public int updateUserById(User user)  throws Exception;
	/**
	 * 根据主键查询用户
	 * 
	 * */
	public User getUserById(Integer id)  throws Exception;
	
	/**
	 * 
	 * 查询用户列表
	 * 
	 * */
	public List<User> selectUserList()  throws Exception;
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public int deleteUserById(Integer id) throws Exception;
	
	
	//以下为redis接口
	public void put(User user) ;
  
    public void delete(User key);
  
    public User get(User key) ;
}
