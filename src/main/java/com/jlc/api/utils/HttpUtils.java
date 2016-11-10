package com.jlc.api.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@SuppressWarnings("unchecked")
public class HttpUtils {
	/*
	 * 获取spring配置的bean实例
	 * 
	 * @Title: getBean
	 * @author dishuan
	 * @date 2013-9-3 上午09:44:15
	 * @param @param request
	 * @param @param beanName
	 * @param @return
	 * @return Object
	 * 
	 */
	public static Object getBean(HttpServletRequest request, String beanName) {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		return ac.getBean(beanName);
	}
	/**
	 * 转换map
	 * @param srcPara
	 * @return
	 */
	public static Map<String, String> getParameter(Map srcPara) {
		Map<String, String> para = new HashMap<String, String>();
		Iterator it = srcPara.keySet().iterator();
		String key;
		String[] strings;
		for (; it.hasNext();) {
			key = (String) it.next();
			strings = (String[]) srcPara.get(key);
			para.put(key, strings[0] == null ? strings[0] : strings[0].trim());
		}
		return para;
	}
	/**
	 * 输出json对象
	 * @param res
	 * @param msg
	 * @throws IOException
	 */
	public static void responseMsg(HttpServletResponse res,Map map){
		try {
			if(map.containsKey("result")&&map.get("result")==null){
				map.put("result", "");
			}
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out=res.getWriter();
			
			Gson gson=new GsonBuilder().serializeNulls().create();
			out.print(gson.toJson(map));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 获取方法
	 * @param methodArray
	 * @return
	 */
	public static Method getMethod(Method[] methodArray,String act){
		Method method = null;
		for (int i = 0; i < methodArray.length; i++) {
			if(methodArray[i].getName().equals(act)){
				method = methodArray[i];
				break;
			}
		}
		return method;
	}
}
