package com.jlc.api.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jlc.api.dto.User;
import com.jlc.api.service.UserService;


@Controller("userAction")
public class UserAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	private List<User> list;
	
	private User user;
	
	private Integer id;
	
	private String name;
	
	public String list() throws Exception{
	   list = userService.selectUserList();	
		return "list";
	}
	
	public String edit() throws Exception{
		 if(id != null){
			 user = userService.getUserById(id);
		 }
		 return "edit";
	}
	
	public void save() throws IOException{
		int returnCode = 1;
		try{
			if(StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
	        User user = new User();
	        if(id != null){
	        	user =  userService.getUserById(id);
	        	user.setName(name);
	        	userService.updateUserById(user);
	        }else{
	        	 user.setName(name);
	        	 userService.addUser(user);
		    }
		}catch(Exception e){
			returnCode = -1;
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/xml;charset=UTF-8");
			  response.getWriter().print(returnCode);
		}
		
	}
	
	public void delete() throws IOException{
		int returnCode = 1;
		try{
			if(id == null){
				returnCode = -2;
				return;
			}
	        userService.deleteUserById(id);
		}catch(Exception e){
			returnCode = -1;
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/xml;charset=UTF-8");
			  response.getWriter().print(returnCode);
		}
		
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
