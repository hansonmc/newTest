package com.jlc.api.myutil;

import com.sun.org.apache.xpath.internal.SourceTree;
import sun.util.calendar.CalendarUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class MyDate {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println( getWeek("2016-11-20"));;
        System.out.println( getMondayOfWeek());
    }
    public static void simpleDemo(){
        //当前日期
        Calendar calendar = Calendar.getInstance();
        StringBuilder str = new StringBuilder();
        str.append("year:" + calendar.get( Calendar.YEAR));
        str.append(" month:" + (calendar.get(calendar.MONTH)+1));
        str.append(" day:" + calendar.get(Calendar.DAY_OF_MONTH));
        str.append(" week:" + calendar.get(Calendar.DAY_OF_WEEK));
        str.append(" hour:" + calendar.get(Calendar.HOUR_OF_DAY));
        str.append(" minute:" + calendar.get(Calendar.MINUTE));
        str.append(" second:" + calendar.get(Calendar.SECOND));
        System.out.println(str);
    }
    public static void showCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1); //设置代表的日期为1号
        int start = calendar.get(Calendar.DAY_OF_WEEK); // 获得1号是星期几
        System.out.println(start);
        int maxDay = calendar.getActualMaximum(Calendar.DATE);// 获得当前月的最大日期数
        System.out.println(maxDay);
    }
    public static void numberOfDays(){
        // 设置两个日期
        // 日期：2009-3-11
        Calendar c1 = Calendar.getInstance();
        c1.set(2009,3-1,11);
        Calendar c2 = Calendar.getInstance();
        // 日期：2010-04-01
        c2.set(2010,4-1,1);
        long t1 = c1.getTimeInMillis();
        long t2 = c2.getTimeInMillis();
        // 计算天数
        long days = (t2-t1)/(24*60*60*1000);
        System.out.println(days + "  " + (24*60*60*1000));
    }

    /**
     * 获得当前年份
     */
    public static int getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    /**
     * 获得当前月份
     */
    public static int getMonth(){
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }
    /**
     * 获得今天在本年的第几天
     */
    public static int getDayOfYear(){
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }
    /**
     * 获得今天在本周的第几天
     */
    public static int getDayOfWeek(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获得今天是这个月的第几周
     */
    public static int getWeekOfMonth(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }
    /**
     * 获得半年后的日期
     */
    public static Date getTimeYearNext(){
     Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,183);
        return calendar.getTime();
    }
    /**
     * 将日期转换成字符串
     */
    public static String convertDateToString(Date dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dateTime);
    }
    /**
     *得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1,String sj2){
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date myDate = myFormatter.parse(sj2);
            day = (date.getTime() - myDate.getTime())/(24*60*60*1000);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return String.valueOf(day);
    }
    /**
     * 根据一个日期，返回是星期几的符串
     */
    public static String getWeek(String sDate){
        //再转换为时间
        Date date = strToDate(sDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
       // return new SimpleDateFormat("EEEE").format(calendar.getTime());
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay+"";
    }
    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     */
    public static Date strToDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strToDate = sdf.parse(strDate,pos);
        return strToDate;
    }
    /**
     * 两时间之间的天数
     */
    public static long getDays(String date1,String date2){
        if(date1 == null || date1.equals("")) return 0;
        if(date2 == null || date2.equals("")) return 0;
        //转换为标准时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date myDate = null;
        try {
            date = sdf.parse(date1);
            myDate = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day = (date.getTime() - myDate.getTime())/(24*60*60*1000);
        return day;
    }
    /**
     * 计算当月最后一天，返回字符串
     */
    public String getDefaultDay(){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);// 设为当前月的1号;
        lastDate.add(Calendar.MONTH,1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE,-1);
        str = sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 上月第一天
     */
    public String getPrevioousMonthFirst(){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);// 设为当前月的1号;
        lastDate.add(Calendar.MONTH,-1);// 减一个月，变为前一个月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 获取当月第一天
     */
    public String getFirstDayOfMonth(){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);// 设为当月的1号;
        str = sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 获得当前日期与本周日相差的天数
     */
    private static int getMondayPlus(){
        Calendar calendar = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期一是第二天......
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek==1){
            return 0;
        }else{
            return 1-dayOfWeek;
        }
    }
    /**
     * 获得本周日的日期
     */
    public static String getCurrentWeekday(){
        //int weeks=0;
        int mondayPlus = getMondayPlus();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,mondayPlus+6);
        return calendar.getTime().toString();
    }

    /**
     * 获得本周一的日期
     */
    public static String getMondayOfWeek(){
        int mondayPlus = getMondayPlus();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK,mondayPlus);
        return calendar.getTime().toString();
    }

}
