package com.jlc.api.utils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.util.StringUtils;

public class DateUtils extends PropertyEditorSupport{
	
	/**
	 * 根据Calendar 获取星期几
	 * @param cal
	 * @return
	 */
	public static String getWeekDay(Calendar cal){
		String retStr = "";
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		switch (weekday) {
			case Calendar.MONDAY: retStr = "星期一" ;break;
			case Calendar.TUESDAY: retStr = "星期二" ;break;
			case Calendar.WEDNESDAY: retStr = "星期三" ;break;
			case Calendar.THURSDAY: retStr = "星期四" ;break;
			case Calendar.FRIDAY: retStr = "星期五" ;break;
			case Calendar.SATURDAY: retStr = "星期六" ;break;
			case Calendar.SUNDAY: retStr = "星期日" ;break;
		}
		return  retStr;
	}
	/**
	 * 字符串时间相比较
	 * @param startTime
	 * @param endTime
	 * @return endTime>=startTime true  反之  false 
	 */
	public static boolean compareDate(String startTime,String endTime){
		if(StringToLong(endTime)>=StringToLong(startTime)){
			return true;
		}
		return false;
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
	 * @param time format
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
	 * 字符串是否解析为Date
	 * @param time format
	 * @return
	 */
	public static boolean parseToDateTrue(String time,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.parse(time);
		} catch (ParseException e) {
			return false;
		}
		return true;
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
	 * 字符串解析为long
	 * @param time
	 * @return
	 */
	public static long StringToLong(String time,String format){
		return StringToDate(time,format).getTime();
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
	 * 获取endTime大约startTime的天数
	 * @param startTime
	 * @param endTime
	 * @return 0：代表startTme>=endTime  >0:实际相差天数
	 */
	public static String getDays(Date startTime,Date endTime){
		long startT = startTime.getTime();
		long endT = endTime.getTime();
		if(endT>startT){
			return Long.toString((endT-startT)/(24*60*60*1000)+1l);
		}else{
			return "0";
		}
	}
	/**
	 * 字符串是否能够转换为日期类型
	 * @param time
	 * @return
	 */
	public static boolean StringParseToDateTrue(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			sdf.parse(time);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 将日期向后添加几天
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date,int days){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);
		return calendar.getTime();
	}
	/**
	 * 获取当月的第一天
	 * @param date
	 * @return
	 */
	public static Date getChangeDay(Date date){
		Calendar   cal_1=Calendar.getInstance();//获取当前日期
        //cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return cal_1.getTime();
	}
	/**
	 * 格式化日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date dayFormat(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 格式化日期
	 * @param date
	 * @param sf
	 * @return
	 */
	public static String FormatDate(Date date, String sf) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateformat = new SimpleDateFormat(sf);
            return dateformat.format(date);
        }
    }
	/**
	 * 格式化日期
	 * @param date
	 * @param sf
	 * @return
	 */
	public static Date DateFormatDate(Date date, String sf) {
        SimpleDateFormat dateformat = new SimpleDateFormat(sf);
        try {
			return dateformat.parse(FormatDate(date, sf));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }
	// 各种时间格式
    public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
        "yyyy-MM-dd");
    // 各种时间格式
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
        "yyyyMMdd");
    
    /**
     * 格式: yyyy-MM 
     */
    public static final SimpleDateFormat yyyy_MM = new SimpleDateFormat(
    "yyyy-MM");
    // 各种时间格式
    public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
        "yyyy年MM月dd日");
    public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
        "yyyyMMddHHmmss");
    public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
        "HH:mm");
    public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    // 以毫秒表示的时间
    private static final long DAY_IN_MILLIS = 86400000;// 一天 24*3600*1000
    private static final long HOUR_IN_MILLIS = 3600000;// 一小时
    private static final long MINUTE_IN_MILLIS = 60000;// 一分钟
    private static final long SECOND_IN_MILLIS = 1000;// 一秒钟

    // 指定模式的时间格式
    private static SimpleDateFormat getSDFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 当前日历，这里用中国时间表示
     * 
     * @return 以当地时区表示的系统当前日历
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 指定毫秒数表示的日历
     * 
     * @param millis
     *                毫秒数
     * @return 指定毫秒数表示的日历
     */
    public static Calendar getCalendar(long millis) {
        Calendar cal = Calendar.getInstance();
        // --------------------cal.setTimeInMillis(millis);
        cal.setTime(new Date(millis));
        return cal;
    }

    // ////////////////////////////////////////////////////////////////////////////
    // getDate
    // 各种方式获取的Date
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 当前日期
     * 
     * @return 系统当前时间
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 指定毫秒数表示的日期
     * 
     * @param millis
     *                毫秒数
     * @return 指定毫秒数表示的日期
     */
    public static Date getDate(long millis) {
        return new Date(millis);
    }

    /**
     * 时间戳转换为字符串
     * 
     * @param time
     * @return
     */
    public static String timestamptoStr(Timestamp time) {
        Date date = null;
        if (null != time) {
            date = new Date(time.getTime());
        }
        return date2Str(date_sdf);
    }

    /**
     * 字符串转换时间戳
     * 
     * @param str
     * @return
     */
    public static Timestamp str2Timestamp(String str) {
        Date date = str2Date(str, date_sdf);
        return new Timestamp(date.getTime());
    }

    /**
     * 字符串转日期型
     * 
     * @param str
     * @param sdf
     * @return
     * 
     * @author zzg16 
     * @date Mar 25, 2014 1:50:11 PM
     */
    public static Date str2Date(String str, SimpleDateFormat sdf) {
        if (null == str || "".equals(str)) {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转换为字符串
     * 
     * @param date
     *                日期
     * @param format
     *                日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, SimpleDateFormat date_sdf) {
        if (null == date) {
            return null;
        }
        return date_sdf.format(date);
    }

    /**
     * 日期转字符串
     * 默认系统当前时间
     * @param date_sdf 格式
     * @return
     * 
     * @author zzg16 
     * @date Apr 9, 2014 11:26:19 AM
     */
    public static String date2Str(SimpleDateFormat date_sdf) {
        Date date = getDate();
        if (null == date) {
            return null;
        }
        return date_sdf.format(date);
    }
    
    /**
     * 格式化时间
     * 
     * @param data
     * @param format
     * @return
     */
    public static String dataformat(String data, String format) {
        SimpleDateFormat sformat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sformat.parse(data);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sformat.format(date);
    }

    /**
     * 日期转换为字符串
     * 
     * @param date
     *                日期
     * @param format
     *                日期格式
     * @return 字符串
     */
    public static String getDate(String format) {
        Date date = new Date();
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 指定毫秒数的时间戳
     * 
     * @param millis
     *                毫秒数
     * @return 指定毫秒数的时间戳
     */
    public static Timestamp getTimestamp(long millis) {
        return new Timestamp(millis);
    }

    /**
     * 以字符形式表示的时间戳
     * 
     * @param time
     *                毫秒数
     * @return 以字符形式表示的时间戳
     */
    public static Timestamp getTimestamp(String time) {
        return new Timestamp(Long.parseLong(time));
    }

    /**
     * 系统当前的时间戳
     * 
     * @return 系统当前的时间戳
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 指定日期的时间戳
     * 
     * @param date
     *                指定日期
     * @return 指定日期的时间戳
     */
    public static Timestamp getTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 指定日历的时间戳
     * 
     * @param cal
     *                指定日历
     * @return 指定日历的时间戳
     */
    public static Timestamp getCalendarTimestamp(Calendar cal) {
        // ---------------------return new Timestamp(cal.getTimeInMillis());
        return new Timestamp(cal.getTime().getTime());
    }

    public static Timestamp gettimestamp() {
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = df.format(dt);
        java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
        return buydate;
    }

    // ////////////////////////////////////////////////////////////////////////////
    // getMillis
    // 各种方式获取的Millis
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 系统时间的毫秒数
     * 
     * @return 系统时间的毫秒数
     */
    public static long getMillis() {
        return new Date().getTime();
    }

    /**
     * 指定日历的毫秒数
     * 
     * @param cal
     *                指定日历
     * @return 指定日历的毫秒数
     */
    public static long getMillis(Calendar cal) {
        // --------------------return cal.getTimeInMillis();
        return cal.getTime().getTime();
    }

    /**
     * 指定日期的毫秒数
     * 
     * @param date
     *                指定日期
     * @return 指定日期的毫秒数
     */
    public static long getMillis(Date date) {
        return date.getTime();
    }

    /**
     * 指定时间戳的毫秒数
     * 
     * @param ts
     *                指定时间戳
     * @return 指定时间戳的毫秒数
     */
    public static long getMillis(Timestamp ts) {
        return ts.getTime();
    }

    // ////////////////////////////////////////////////////////////////////////////
    // formatDate
    // 将日期按照一定的格式转化为字符串
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 默认方式表示的系统当前日期，具体格式：年-月-日
     * 
     * @return 默认日期按“年-月-日“格式显示
     */
    public static String formatDate() {
        return date_sdf.format(getCalendar().getTime());
    }

    /**
     * 获取时间字符串
     */
    public static String getDataString(SimpleDateFormat formatstr) {
        return formatstr.format(getCalendar().getTime());
    }

    /**
     * 指定日期的默认显示，具体格式：年-月-日
     * 
     * @param cal
     *                指定的日期
     * @return 指定日期按“年-月-日“格式显示
     */
    public static String formatDate(Calendar cal) {
        return date_sdf.format(cal.getTime());
    }

    /**
     * 指定日期的默认显示，具体格式：年-月-日
     * 
     * @param date
     *                指定的日期
     * @return 指定日期按“年-月-日“格式显示
     */
    public static String formatDate(Date date) {
        return date_sdf.format(date);
    }

    /**
     * 指定毫秒数表示日期的默认显示，具体格式：年-月-日
     * 
     * @param millis
     *                指定的毫秒数
     * @return 指定毫秒数表示日期按“年-月-日“格式显示
     */
    public static String formatDate(long millis) {
        return date_sdf.format(new Date(millis));
    }

    /**
     * 默认日期按指定格式显示
     * 
     * @param pattern
     *                指定的格式
     * @return 默认日期按指定格式显示
     */
    public static String formatDate(String pattern) {
        return getSDFormat(pattern).format(getCalendar().getTime());
    }

    /**
     * 指定日期按指定格式显示
     * 
     * @param cal
     *                指定的日期
     * @param pattern
     *                指定的格式
     * @return 指定日期按指定格式显示
     */
    public static String formatDate(Calendar cal, String pattern) {
        return getSDFormat(pattern).format(cal.getTime());
    }

    /**
     * 指定日期按指定格式显示
     * 
     * @param date
     *                指定的日期
     * @param pattern
     *                指定的格式
     * @return 指定日期按指定格式显示
     */
    public static String formatDate(Date date, String pattern) {
        return getSDFormat(pattern).format(date);
    }

    // ////////////////////////////////////////////////////////////////////////////
    // formatTime
    // 将日期按照一定的格式转化为字符串
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 默认方式表示的系统当前日期，具体格式：年-月-日 时：分
     * 
     * @return 默认日期按“年-月-日 时：分“格式显示
     */
    public static String formatTime() {
        return time_sdf.format(getCalendar().getTime());
    }

    /**
     * 指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分
     * 
     * @param millis
     *                指定的毫秒数
     * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
     */
    public static String formatTime(long millis) {
        return time_sdf.format(new Date(millis));
    }

    /**
     * 指定日期的默认显示，具体格式：年-月-日 时：分
     * 
     * @param cal
     *                指定的日期
     * @return 指定日期按“年-月-日 时：分“格式显示
     */
    public static String formatTime(Calendar cal) {
        return time_sdf.format(cal.getTime());
    }

    /**
     * 指定日期的默认显示，具体格式：年-月-日 时：分
     * 
     * @param date
     *                指定的日期
     * @return 指定日期按“年-月-日 时：分“格式显示
     */
    public static String formatTime(Date date) {
        return time_sdf.format(date);
    }

    // ////////////////////////////////////////////////////////////////////////////
    // formatShortTime
    // 将日期按照一定的格式转化为字符串
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 默认方式表示的系统当前日期，具体格式：时：分
     * 
     * @return 默认日期按“时：分“格式显示
     */
    public static String formatShortTime() {
        return short_time_sdf.format(getCalendar().getTime());
    }

    /**
     * 指定毫秒数表示日期的默认显示，具体格式：时：分
     * 
     * @param millis
     *                指定的毫秒数
     * @return 指定毫秒数表示日期按“时：分“格式显示
     */
    public static String formatShortTime(long millis) {
        return short_time_sdf.format(new Date(millis));
    }

    /**
     * 指定日期的默认显示，具体格式：时：分
     * 
     * @param cal
     *                指定的日期
     * @return 指定日期按“时：分“格式显示
     */
    public static String formatShortTime(Calendar cal) {
        return short_time_sdf.format(cal.getTime());
    }

    /**
     * 指定日期的默认显示，具体格式：时：分
     * 
     * @param date
     *                指定的日期
     * @return 指定日期按“时：分“格式显示
     */
    public static String formatShortTime(Date date) {
        return short_time_sdf.format(date);
    }

    // ////////////////////////////////////////////////////////////////////////////
    // parseDate
    // parseCalendar
    // parseTimestamp
    // 将字符串按照一定的格式转化为日期或时间
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
     * 
     * @param src
     *                将要转换的原始字符窜
     * @param pattern
     *                转换的匹配格式
     * @return 如果转换成功则返回转换后的日期
     * @throws ParseException
     * @throws AIDateFormatException
     */
    public static Date parseDate(String src, String pattern)
        throws ParseException {
        return getSDFormat(pattern).parse(src);

    }

    /**
     * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
     * 
     * @param src
     *                将要转换的原始字符窜
     * @param pattern
     *                转换的匹配格式
     * @return 如果转换成功则返回转换后的日期
     * @throws ParseException
     * @throws AIDateFormatException
     */
    public static Calendar parseCalendar(String src, String pattern)
        throws ParseException {

        Date date = parseDate(src, pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String formatAddDate(String src, String pattern, int amount) throws ParseException
         {
        Calendar cal;
        cal = parseCalendar(src, pattern);
        cal.add(Calendar.DATE, amount);
        return formatDate(cal);
    }
    
    public static String formatAddDayIncludeCurr(String src, String pattern, int amount)
        throws ParseException {
        Calendar cal;
        cal = parseCalendar(src, pattern);
        cal.add(Calendar.DATE, amount-1);
        return formatDate(cal);
    }
    
    public static String formatAddMonth(String src, String pattern, int amount)
        throws ParseException {
        Calendar cal;
        cal = parseCalendar(src, pattern);
        cal.add(Calendar.MONTH, amount);
        return formatDate(cal);
    }
    
    public static String formatAddDayIncludeCurrWithHoliday(String src, String pattern, int amount,List<String> holiday)
            throws ParseException {
            Calendar cal;
            cal = parseCalendar(src, pattern);
            cal.add(Calendar.DATE, amount-1);
            cal.add(Calendar.DATE, delayHoliday(cal,holiday));
            return formatDate(cal);
    }
	
    /**
     * 节假日顺延 
     */
	public static int delayHoliday(Calendar calendar, List<String> holiday) {

		int dayForWeek = 0;
		int delayDay = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(calendar.getTime());
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		}

		while (holiday.contains(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())) || dayForWeek == 6 || dayForWeek == 7) {
			if (holiday.contains(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()))) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				delayDay++;
			}
			if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				dayForWeek = 7;
			} else {
				dayForWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
			}
			
			if (dayForWeek == 6) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
				delayDay = delayDay + 2;
			}
			if (dayForWeek == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				delayDay = delayDay + 1;
			}
		}

		return delayDay;
	}

	
    /**
     * 计算还款日期，节假日顺延
     * 
     * @param src
     *                还款开始日期
     * @param pattern
     *                转换的匹配格式
     * @param amount
     *                还款月期数
     * @param holiday 
     *                节假日列表
     * @return 如果转换成功则返回转换后的日期
     * @throws ParseException
     */
	public static String formatAddMonthWithHoliday(String src, String pattern, int amount,List<String> holiday) throws ParseException {
		Calendar cal;
		cal = parseCalendar(src, pattern);
		cal.add(Calendar.MONTH, amount);
		cal.add(Calendar.DATE, delayHoliday(cal,holiday));
		return formatDate(cal);
	}
    
    public static String formatAddYear(String src, String pattern, int amount)
        throws ParseException {
        Calendar cal;
        cal = parseCalendar(src, pattern);
        cal.add(Calendar.YEAR, amount);
        return formatDate(cal);
    }

    /**
     * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
     * 
     * @param src
     *                将要转换的原始字符窜
     * @param pattern
     *                转换的匹配格式
     * @return 如果转换成功则返回转换后的时间戳
     * @throws ParseException
     * @throws AIDateFormatException
     */
    public static Timestamp parseTimestamp(String src, String pattern)
        throws ParseException {
        Date date = parseDate(src, pattern);
        return new Timestamp(date.getTime());
    }

    // ////////////////////////////////////////////////////////////////////////////
    // dateDiff
    // 计算两个日期之间的差值
    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 计算两个时间之间的差值，根据标志的不同而不同
     * 
     * @param flag
     *                计算标志，表示按照年/月/日/时/分/秒等计算
     * @param calSrc
     *                减数
     * @param calDes
     *                被减数
     * @return 两个日期之间的差值
     */
    public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

        long millisDiff = getMillis(calSrc) - getMillis(calDes);

        if (flag == 'y') {
            return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
        }

        if (flag == 'd') {
            return (int) (millisDiff / DAY_IN_MILLIS);
        }

        if (flag == 'h') {
            return (int) (millisDiff / HOUR_IN_MILLIS);
        }

        if (flag == 'm') {
            return (int) (millisDiff / MINUTE_IN_MILLIS);
        }

        if (flag == 's') {
            return (int) (millisDiff / SECOND_IN_MILLIS);
        }

        return 0;
    }

    /**
     * String类型 转换为Date, 如果参数长度为10 转换格式”yyyy-MM-dd“ 如果参数长度为19 转换格式”yyyy-MM-dd
     * HH:mm:ss“ *
     * 
     * @param text
     *                String类型的时间值
     */
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            try {
                if (text.indexOf(":") == -1 && text.length() == 10) {
                    setValue(this.date_sdf.parse(text));
                } else if (text.indexOf(":") > 0 && text.length() == 19) {
                    setValue(this.datetimeFormat.parse(text));
                } else {
                    throw new IllegalArgumentException(
                        "Could not parse date, date format is error ");
                }
            } catch (ParseException ex) {
                IllegalArgumentException iae = new IllegalArgumentException(
                    "Could not parse date: " + ex.getMessage());
                iae.initCause(ex);
                throw iae;
            }
        } else {
            setValue(null);
        }
    }

    public static int getYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getDate());
        return calendar.get(Calendar.YEAR);
    }

    public static boolean betweenDate(Date startDate, Date endDate) {
        Date currentDate = new Date();
        if (startDate == null) {
            return betweenDate(endDate);
        } else if (startDate.getTime() - currentDate.getTime() <= 0
            && currentDate.getTime() - endDate.getTime() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean betweenDate(Date endDate) {
        Date currentDate = new Date();
        if (currentDate.getTime() - endDate.getTime() <= 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 比较日期大小
     * @param DATE1
     * @param DATE2
     * @return
     */
	 public static Boolean compare_date(Date DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        Boolean flag=true;
	        try {
	            Date dt2 = df.parse(DATE2);
	            if (DATE1.getTime() >= dt2.getTime()) {
	                flag=true;
	            } else  {
	                flag=false;
	            }  
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	         return flag;
    }
	 
	 /**
		 * 判断  当时间时间的时分秒  是否  在两个时间（时分秒）之间
		 * @param nowTime  当前时间   年月日  时分秒
		 * @param compareDate1   比较时间（小）
		 * @param compareDate2 比较时间（大）
		 * @return
		 */
		public static boolean compareBetweenDate(Date nowTime, String compareDate1,String compareDate2) {
		        boolean flag=false;
		        try {
		        	DateFormat df = new SimpleDateFormat("HH:mm:ss");
		        	Date now = df.parse(df.format(nowTime));//当前时分秒
		            Date tempDate1 = df.parse(compareDate1);//比较时间（小）
		            Date tempDate2 = df.parse(compareDate2);//比较时间（大）
		            if (now.getTime() > tempDate1.getTime() && now.getTime() < tempDate2.getTime()) {
		                flag = true;
		            }else {
		            	flag = false;
		            }
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
		       return flag;
		    }
   
		
	public static Date getNextZeroHourTime() {
	     Calendar cal = Calendar.getInstance();
	     cal.setTime(new Date());
	     cal.set(Calendar.HOUR_OF_DAY, 23);
	     cal.set(Calendar.MINUTE, 59);
	     cal.set(Calendar.SECOND, 59);
//	     cal.set(Calendar.MILLISECOND, 0);
//	     cal.add(Calendar.DAY_OF_MONTH, 1);
	     return  cal.getTime();
	}
    public static void main(String[] args) {
        System.out.println(getNextZeroHourTime());
    }
}
