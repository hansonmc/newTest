package com.jlc.api.util.noteplatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SmsChannelFactory {
	
	private static int cacheTime  = 0;
	
	private static SmsChannelFactory instance = new  SmsChannelFactory();
	
	//短信通道列表
	private static List<SmsChannel> smsChannelList = new ArrayList<SmsChannel>();
	
	//手机号最后一次发送通道
	private static Map<String,SmsChannel> mobileChannel = new HashMap<String,SmsChannel>();
	
	private static String signature;
	
	private static final String SIGNATURE_DEFAULT = "【金联储】";
	
	public  Map<String,SmsChannel>  getMobileChannel() {
		if(mobileChannel == null){
			mobileChannel = new HashMap<String,SmsChannel>();
		}
		return mobileChannel;
	}
	
	public int getCacheTime() {
		
		if(cacheTime == 0){
			cacheTime = ConfigReader.getCacheTime();
		}
		
		return cacheTime;
	}

	public String getSignature() {
		
		if(StringUtils.isBlank(signature)){
			signature = ConfigReader.getSignature();
		}
		
		return signature;
	}

	public static SmsChannelFactory getInstance(){
		if(instance == null){
			instance = new  SmsChannelFactory();
		}
		return instance;
	}
	
	public List<SmsChannel> getSmsChannelList(){
		
		if(smsChannelList == null){
			smsChannelList = new ArrayList<SmsChannel>();
		}
		
		if(smsChannelList.size() == 0){
			String smsChannelstr = ConfigReader.getSmsChannelList();
			if(StringUtils.isNotBlank(smsChannelstr)){
				JSONArray list = JSONArray.parseArray(smsChannelstr);	
				for(int i=0;i<list.size();i++){
					
					JSONObject ent = list.getJSONObject(i);
					
					if(StringUtils.isBlank(ent.getString("status")) || ent.getString("status").equals("0"))continue;
					
					SmsChannel smsChannel = new SmsChannel();
					
					smsChannel.setIndex(i);
					smsChannel.setCode(ent.getString("code"));
					try{
						smsChannel.setName(new String(ent.getString("name").getBytes(),"utf-8"));
					}catch(Exception e){
						
					}
					smsChannel.setStatus(ent.getString("status"));
					
					smsChannelList.add(smsChannel);
				}
			}
		}
		
		return smsChannelList;
	}
	
}
