package com.jlc.api.dao;

import com.jlc.api.dto.AppToken;

public interface AppTokenMapper {
	
	public void insertToken(AppToken token);
	
	public int deleteToken(Long userId);
	
	public int updateToken(AppToken token);
	
	public AppToken getTokenByUserId(Long userId);

	public AppToken getTokenByToken(String token);
}
