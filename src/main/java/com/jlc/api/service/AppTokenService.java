package com.jlc.api.service;

import com.jlc.api.dto.AppToken;

public interface AppTokenService {
	public void saveToken(Long userId,String Token);
	
	public int updateToken(AppToken token);
	
	public AppToken getTokenByToken(String token);
}
