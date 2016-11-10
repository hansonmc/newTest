package com.jlc.api.util.noteplatform;

import java.util.HashMap;
import java.util.Map;

public class SmsSendCount {
	
	/**
	 * 单次请求短信发送次数
	 * 
	 */
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(); 
	
	public static void initialValue(){
		threadLocal.set(0);
	}
	
	public static void set(Integer value){
		threadLocal.set(value);
	}
	
	public static Integer get(){
		return threadLocal.get();
	}
	
	public static void remove(){
		threadLocal.remove();
	}
	
	/**
	 * 短信发送通道
	 * key0 channel:空代表漫道;0:代表蓝创
	 * key1 msgid:短信id
	 * 
	 * */
	private static ThreadLocal<Map<String,String>> threadLocal_channel = new ThreadLocal<Map<String,String>>(); 
	
	public static void initial_channelValue(){
		threadLocal_channel.set(new HashMap<String,String>());
	}
	
	public static void set_channel(String key,String value){
		if(threadLocal_channel.get() == null){
			initial_channelValue();
		}
		threadLocal_channel.get().put(key, value);
	}
	
	public static Map<String,String> get_channel(){
		return threadLocal_channel.get();
	}
	
	public static void remove_channel(){
		threadLocal_channel.remove();
	}
	
	

}
