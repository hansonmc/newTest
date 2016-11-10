package com.jlc.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;


public class InterfaceUtil {
	
	
	/**
	 * 根据参数拼接接口url
	 * urlMod url模块名称
	 * map 参数集合
	 */
	
    @SuppressWarnings("unused")
	public static com.alibaba.fastjson.JSONObject getUrl(String requrl, String urlMod, Map<String, String> map) {
    	Logger log = Logger.getLogger(InterfaceUtil.class);
    	com.alibaba.fastjson.JSONObject json = null;
    	
    	//拼接访问ebankApp接口的完整url
    	StringBuilder url = new StringBuilder(requrl);
    	url.append(urlMod);
    	url.append(".html?");
    	
    	System.out.println("请求红包地址："+url.toString());
    	
    	if (!map.isEmpty()) {
    		int i = 0;
    		for (Entry<String, String> entry: map.entrySet()) {
    		    String key = entry.getKey();
    		    String value = entry.getValue();
    		    if(i == 0){
    		    	url.append(key+"="+value);
    		    }else{
    		    	url.append("&"+key+"="+value);
    		    }
    		    i++;
    		}
		}
    	
    	
		try {
			URL u = new URL(url.toString());
			log.info("getRedPacketURL:"+url.toString());
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			connection.setDoInput(true);
			// Set the post method. Default is GET
			connection.setRequestMethod("POST");
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.connect();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String lines;
			Map<String,String> result=new HashMap<String, String>();
			while ((lines = reader.readLine()) != null) {
				json = JSON.parseObject(lines);
			}
			log.info("getRedPacketURLJson:"+json);
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return json;
    }
    
    
    /**
	 * 根据参数拼接接口url
	 * 
	 */
	public static JSONObject getUrl(String url) {
    	Logger log = Logger.getLogger(InterfaceUtil.class);
    	JSONObject json = null;
		try {
			URL u = new URL(url);
			log.info("-------------InterfaceUtil.getUrl:"+url.toString());
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			connection.setDoInput(true);
			// Set the post method. Default is GET
			connection.setRequestMethod("POST");
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				json = new JSONObject(lines);
			}
			connection.disconnect();
		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return json;
    }
    
    
    
    
    
    //测试方法
//    public static void main(String[] args) {
//    	String url="http://192.168.20.107:8080/ebankApp/invest.html?mod=invest&act=getInvestList&page=1&pageSize=10&t=t&sign=sing";
//		try {
//			URL u = new URL(url.toString());
//			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setInstanceFollowRedirects(false);
//			connection.connect();
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//			String lines;
//			String code;
//			String message;
//			Map<String,String> result=new HashMap<String, String>();
//			while ((lines = reader.readLine()) != null) {
//				JSONObject json = JSONObject.fromObject(lines);
//				if (json!=null) {
//					code=json.get("code").toString();
//					message=json.get("message").toString();
//					JSONArray array=json.getJSONArray("result");
//					JSONObject object=array.getJSONObject(0);
//					if (array.size()>0) {
//						result=(Map<String, String>)array.get(0);
//					}
//				}
//			}
//			connection.disconnect();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}