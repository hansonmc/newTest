package com.jlc.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;



public class TestSweep {

    
	public static void main(String[] args) {
		List<Thread> list = new ArrayList<Thread>(); 
		
		for(int i = 0;i<1;i++){
//			
			Thread th1 = new Thread(new Runnable() {
				@Override
				public void run() {
					postUrl(2);
				}
			});
			th1.start();
			list.add(th1);
//			Thread th2 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					postUrl(1);
//				}
//			});
//			th2.start();
//			list.add(th2);
		}
		for(Thread th :list){
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	


	
	private static void postUrl(int type){
		try {
			JSONObject json = new JSONObject();
			URL u = null;
			if(type==1){
				u = new URL("http://localhost:8080/api/sweepstake/sweepstake.dhtml?aId=101&token=QG4BYAU38F9AMBT27Q9VMV6UD9EL1QFB,116915");
			}else{
				u = new URL("http://localhost:8080/api/sweepstake/sweepstake.dhtml?aId=101&phone=10900000004");
			}
			
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
				json = JSONObject.fromObject(lines);
			}
			System.out.println("##########################"+json);
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
