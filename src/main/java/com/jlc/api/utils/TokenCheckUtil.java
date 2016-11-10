package com.jlc.api.utils;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jlc.api.cache.memcached.CacheKey;
import com.jlc.api.cache.memcached.IMemcachedClient;
import com.jlc.api.cache.memcached.impl.MemcachedClientImpl;
import com.jlc.api.dto.AppToken;
import com.jlc.api.service.AppTokenService;

public class TokenCheckUtil{
    
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TokenCheckUtil.class);
	
	
	/**
	 * 验证token
	 * @param invocation
	 * @return 0:token不合法  1：token合法
	 * @throws Exception
	 */
	public static boolean checkToken(String token) throws Exception {
		if(token==null){
			log.error("没有传递token");
			return false;
		}
		
		WebApplicationContext applicationContext =WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		
		//对于拦截的方法进行处理
        try{
        	//token格式：  uuid,id
    		String[] info = token.split(",");
    		String uuid = info[0];
    	    String id = info[1];
			//如果token验证成功且没有过期直接放行,key:id,value:uuid
			IMemcachedClient client = (MemcachedClientImpl)applicationContext.getBean("cache");
			//key:APP_TOKEN_id,value:uuid
			String key = CacheKey.TOKEN_PREFIX+id;
			boolean flag = client.exist(key);
			if(flag){
				String value = (String)client.get(key);
				if(uuid.equals(value)){
					//memcached源码中，不超过30天，是相对时间，超过30天，是绝对时间，单位是s
					Integer memExpireTime = Integer.valueOf(ConstantUtil.INSTANCE.getTokenValidTime());
					//重新设置失效时间,单位为秒,相对时间
					client.set(key,uuid,memExpireTime);
					return true;
				}else{
					log.error("memcached中token和id不匹配！");
					return false;
				}
			}else{
				//同样id和uuid必须匹配
				AppTokenService tokenDao = (AppTokenService)applicationContext.getBean("appTokenService");
				
				//userid和token匹配并且当前时间在失效时间之前方可放行
				AppToken apptoken = tokenDao.getTokenByToken(uuid);
				if(apptoken!=null){
					Long userid = apptoken.getUserId();
					Long expireTime = apptoken.getExpired().getTime();
					Long currentTime = System.currentTimeMillis();
					
					if(userid.equals(Long.parseLong(id))){
						if(currentTime<expireTime){
							//新的过期时间为：当前时间+过期时间
							Long latestExpireTime = currentTime+Long.parseLong(ConstantUtil.INSTANCE.getTokenValidTime())*1000;
							apptoken.setExpired(new Date(latestExpireTime));//更新失效时间
							apptoken.setDescribe("无法从缓存取到数据");
							tokenDao.updateToken(apptoken);
							return true;
						}else{
							log.error("id和token匹配，但时间失效！");
							return false;
						}
					}else{
						log.error("id和token不匹配！");
						return false;
					}
				}else{
					log.error("token表中不存在该记录！");
					return false;
				}
			}
		}catch(Exception e){
			log.error("token处理过程中出现失败！");
			e.printStackTrace();
			return false;
		}
	}
	
}
