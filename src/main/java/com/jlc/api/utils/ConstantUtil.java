package com.jlc.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取constants.properties文件数据
 * 
 * 
 * 
 */
public enum ConstantUtil {
	INSTANCE;
	private final Properties PROPERTIES = new Properties();

	private ConstantUtil() {
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/constants.properties");
			PROPERTIES.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			in = null;
		}
	}

	/**
	 * 获取constant.properties里数据
	 * 
	 * @param key
	 * @return
	 * @see constant.properties
	 */
	public String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}

	public String getTokenValidTime() {
		return PROPERTIES.getProperty("token_valid_time");
	}

	public Integer getDictionaryCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("dictionary_time"));
		return time;
	}
	
	public Integer getActiviteCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("activite_time"));
		return time;
	}
	
	public Integer getRegulationCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("regulation_time"));
		return time;
	}
	
	public Integer getWinRecordListCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("WinRecordList_time"));
		return time;
	}
	
	public Integer getActiviteConditionsCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("ActiviteConditions_time"));
		return time;
	}
	
	public Integer getCjTimeCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("CjTime_time"));
		return time;
	}
	
	public Integer getMemberInfoCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("MemberInfo_time"));
		return time;
	}
	
	public Integer getDialogUrlCacheTime(){
		Integer time = Integer.valueOf(PROPERTIES.getProperty("GetDialogUrl_time"));
		return time;
	}
	
	public String getRedisIP(){
		String ip = PROPERTIES.getProperty("redis_ip");
		return ip;
	}
	
	
	public static void main(String[] args) {
		//String s = ConstantUtil.INSTANCE.getTokenValidTime();
		//System.out.println(s);
	}
}