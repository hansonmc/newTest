package com.jlc.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.api.dao.AppTokenMapper;
import com.jlc.api.dto.AppToken;
import com.jlc.api.service.AppTokenService;
import com.jlc.api.utils.ConstantUtil;

@Service("appTokenService")
public class AppTokenServiceImpl implements AppTokenService{

	@Autowired
	private AppTokenMapper appTokenMapper;
	
	@Override
	public void saveToken(Long userId, String token) {
		AppToken oldToken = appTokenMapper.getTokenByUserId(userId);
		if(oldToken != null){
			//1.已存在的记录未过期 (比如app登录后在M站登录)
			//2.已经过期(超过7天未登录)
			//两种情况操作一样，更新token
			oldToken.setToken(token);
			oldToken.setExpired(new Date(System.currentTimeMillis() + Integer.parseInt(ConstantUtil.INSTANCE.getTokenValidTime()) * 1000 ));
			oldToken.setDescribe("登录更新token");
			appTokenMapper.updateToken(oldToken);
		}else{
			AppToken tokenObj = new AppToken();
			tokenObj.setUserId(userId);
			tokenObj.setToken(token);
			tokenObj.setExpired(new Date(System.currentTimeMillis() +  Integer.parseInt(ConstantUtil.INSTANCE.getTokenValidTime()) * 1000 ));
			tokenObj.setDescribe("登录新增token");
			appTokenMapper.insertToken(tokenObj);
		}
	}

	@Override
	public int updateToken(AppToken token) {
		// TODO Auto-generated method stub
		return appTokenMapper.updateToken(token);
	}

	@Override
	public AppToken getTokenByToken(String token) {
		// TODO Auto-generated method stub
		return appTokenMapper.getTokenByToken(token);
	}

}
