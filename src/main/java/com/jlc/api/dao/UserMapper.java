package com.jlc.api.dao;

import java.util.List;

import com.jlc.api.dto.User;



public interface UserMapper {  
	
	/**
	 * 增加用户
	 * 
	 * */
	public int addUser(User user);
	/**
	 * 修改用户
	 * 
	 * */
	public int updateUserById(User user);
	/**
	 * 根据主键查询用户
	 * 
	 * */
	public User getUserById(Integer id);
	
	/**
	 * 
	 * 查询用户列表
	 * 
	 * */
	public List<User> selectUserList();
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public int deleteUserById(Integer id) throws Exception;

}

