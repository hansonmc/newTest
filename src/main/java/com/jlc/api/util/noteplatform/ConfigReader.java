package com.jlc.api.util.noteplatform;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigReader {
	
	private static Logger logger = Logger.getLogger(ConfigReader.class);
	
	private static String smsChannelList = "";
	
	private static int cacheTime  = 90;
	
	private static String signature = "【金联储】";

	static {
		logger.info("The file \"smsChannel.properties\" analyse start!");
		try{
			InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("smsChannel.properties"); 
			BufferedReader br= new BufferedReader(new InputStreamReader(is));  
	        Properties props = new Properties();  
	        props.load(br);  
	        smsChannelList = props.getProperty("smsChannelList"); 
	        cacheTime = Integer.valueOf(props.getProperty("cacheTime")); 
	        signature = props.getProperty("signature");
		}catch(Exception e){
			logger.info("The file \"smsChannel.properties\" analyse  failure !");
		}
		logger.info("The file \"smsChannel.properties\" analyse end!");
	}

	public static String getSmsChannelList() {
		return smsChannelList;
	}
	
	public static int getCacheTime() {
		return cacheTime;
	}

	public static String getSignature() {
		return signature;
	}
	

}
