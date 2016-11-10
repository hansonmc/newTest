package com.jlc.api.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;

public class StringUtils {
	
	//获取IP
	  public static String getIpAddr(HttpServletRequest request) {      
	       String ip = request.getHeader("x-forwarded-for");      
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("Proxy-Client-IP");      
	      }      
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	      }      
	     return ip;      
	}  	
	
	/**
	 * bigdecimal 添加小数点
	 * @param bg
	 * @return
	 */
	public static BigDecimal AddDecimals(BigDecimal bg){
		if(bg==null){
			bg = new BigDecimal(0.00);
		}
		String bgStr = bg.toString();
		int index = bgStr.indexOf(".");
		if(index<=0){
			bgStr = bgStr+".00";
		}
		return new BigDecimal(bgStr);
	} 
	
	/**
	 * 字符串是否为空
	 * @param str
	 * @return true  为空    false  不为空
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 字符串是否为空
	 * @param str
	 * @return true  为空    false  不为空
	 */
	public static boolean isEmpty2(String str){
		if(str==null||str.equals("")||str.equals("null")){
			return true;
		}
		return false;
	}
	/**
	 * 字符串是否解析为整型
	 * @param str
	 * @return  true：解析成功    false：解析失败
	 */
	public static boolean parseToIntTrue(String str){
		try {
			Integer.valueOf(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	} 
	/**
	 * 字符串解析为整型
	 * @param str
	 * @return Integer
	 */
	public static Integer parseToInt(String str){
		if(!parseToIntTrue(str)){
			return null;
		}
		return Integer.valueOf(str);
	}
	/**
	 * 字符串是否解析为正整数(整形)2*31-1~-2*31  (20亿左右)
	 * @param str
	 * @return
	 */
	public static boolean parseToPositiveITrue(String str){
		if(parseToInt(str)!=null){
			if(parseToInt(str)>=0){
				return true;
			}
		}
		return false;
	}
	/**
	 * 字符串是否解析为正整数(长整形)2*64-1~-2*64
	 * @param str
	 * @return
	 */
	public static boolean parseToPositiveLTrue(String str){
		if(parseToLongTrue(str)){
			if(parseToLong(str)>=0){
				return true;
			}
		}
		return false;
	}
	/**
	 * 字符串是否解析为正整数
	 * @param str
	 * @return  true：解析成功    false：解析失败
	 */
	public static boolean parseToPositiveTrue(String str){
		if(parseToInt(str)!=null){
			if(parseToInt(str)>0){
				return true;
			}
		}
		return false;
	}
	/**
	 * 字符串解析为正整型
	 * @param str
	 * @return Integer
	 */
	public static Integer parseToPositive(String str){
		if(!parseToPositiveTrue(str)){
			return null;
		}
		return parseToInt(str);
	}

	/**
	 * 判断字符串是否解析为Date
	 * @param date
	 * @param format
	 * @return true：解析成功    false：解析失败
	 */
	public static boolean parseToDateTrue(String date,String format){
		boolean boo = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.parse(date);
			boo = true;
		} catch (ParseException e) {
			boo = false;
		}
		return boo;
	}
	/**
	 * 字符串是否解析为Date
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date parseToDate(String date,String format){
		Date afterDate = null ;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			afterDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return afterDate;
	}
	/**
	 * 字符串是否解析为Long型
	 * @param str
	 * @return  true：解析成功    false：解析失败
	 */
	public static boolean parseToLongTrue(String str){
		try {
			Long.parseLong(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	} 
	/**
	 * 字符串解析为Long型
	 * @param str
	 * @return Long
	 */
	public static Long parseToLong(String str){
		if(!parseToLongTrue(str)){
			return null;
		}
		return Long.parseLong(str);
	}
	/**
	 * 字符串解析为Date
	 * @param time
	 * @return
	 */
	public static Date StringToDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 字符串解析为Date
	 * @param time
	 * @return
	 */
	public static Date StringToDate(String time,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Date解析为字符串
	 * @param time
	 * @return
	 */
	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}
	/**
	 * 字符串解析为long
	 * @param time
	 * @return
	 */
	public static long StringToLong(String time){
		return StringToDate(time).getTime();
	}
	/**
	 * long型解析为Date
	 * @param lon
	 * @return
	 */
	public static Date longToDate(long lon){
		return new Date(lon);
	}
	/**
	 * long型解析为String
	 * @param lon
	 * @return
	 */
	public static String longToString(long lon){
		return new Date(lon).toString();
	}
	/**
	 * 日期相加相减操作
	 * @param date
	 * @return
	 */
	public static Date dateOperate(Date date){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH,-12);//日期减12个月
		return rightNow.getTime();
	}
	/**
	 * 获取字符串中出现某个字符的次数
	 * @param str
	 * @param c
	 * @return 0没有此字符 
	 */
	public static int getIndexs(String str,char c){
		str.charAt(1);
		int j = 0;
		char[] cArr = str.toCharArray();
		for (int i = 0; i < cArr.length; i++) {
			if(cArr[i]==c){
				j++;
			}
		}
		return j;
	}
	 /**
     * 随即生成指定位数的含数字验证码字符串
     * 
     * @author Peltason
     * @date 2007-5-9
     * @param bit
     *                指定生成验证码位数
     * @return String
     */
    public static String numRandom(int bit) {
        if (bit == 0)
            bit = 6; // 默认6位
        String str = "";
        str = "0123456789";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }
    
    
    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){  
    	  for (int i = str.length();--i>=0;){    
    	   if (!Character.isDigit(str.charAt(i))){  
    	    return false;  
    	   }  
    	  }  
    	  return true;  
    	}

	/**
	 * 获取带*号匿名的字符串
	 *
	 * @param text
	 * @return
	 */
	public static String getHiddenString(String text) {
		//为空时直接替换为**
		if (text == null || text.isEmpty())
			return "**";
		//位数为2时直接去第一个字符加*
		if (text.length() < 3) {
			return text.charAt(0) + "*";
		}
		//取首尾加**
		return String.format("%c**%c", text.charAt(0), text.charAt(text.length() - 1));
	}
}
