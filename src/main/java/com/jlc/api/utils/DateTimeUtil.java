package com.jlc.api.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTimeUtil {
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_FORMAT_HOUR = "yyyy-MM-dd HH:00:00";
    public final static String DATE_FORMAT_CN = "yyyy??MM??dd??";
    public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String TIME_FORMAT_CN = "yyyy??MM??dd?? HH:mm:ss";
    public final static String MONTH_FORMAT = "yyyy-MM";
    public final static String DAY_FORMAT = "yyyyMMdd";
    public final static String TIME_HOUR = "k";
    public final static String DAY_SIMPLE_FORMAT = "dd";
    public final static String DAY_SIMPLE_FORMAT_D = "d";

    // private final static String TIME_FORMAT_MILLI = "yyyy-MM-dd
    // HH:mm:ss.SSS";

    public static java.util.Date getCurrDate() {
        return new java.util.Date();
    }

    public static java.sql.Timestamp getCurrTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    public static java.sql.Timestamp getFormatTimestamp(String currDate) {
        Date date = getFormatDateTime(currDate);
        return new java.sql.Timestamp(date.getTime());
    }

    public static String getFormatDate(java.util.Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    public static Date getFormatDateToDate(java.util.Date currDate) {
        return getFormatDate(getFormatDate(currDate));
    }

    public static String getFormatDate_CN(java.util.Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    public static Date getFormatDateToDate_CN(java.util.Date currDate) {
        return getFormatDate_CN(getFormatDate_CN(currDate));
    }

    public static Date getFormatDate(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    public static Date getFormatDate_CN(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    public static String getFormatDate(java.util.Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            String str = dtFormatdB.format(currDate);
            if (str.equals("24"))
                str = "0";
            return str;
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            return dtFormatdB.format(currDate);
        }
    }

    public static String getFormatDateTime(java.util.Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    public static String getFormatDateHour(java.util.Date currDate) {
        return getFormatDateTime(currDate, DATE_FORMAT_HOUR);
    }

    public static Date getFormatDateHour(String currDate) {
        return getFormatDateTime(currDate, DATE_FORMAT_HOUR);
    }

    public static Date getFormatDateTimeToTime(java.util.Date currDate) {
        return getFormatDateTime(getFormatDateTime(currDate));
    }

    public static Date getFormatDateTime(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    public static String getFormatDateTime_CN(java.util.Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    public static Date getFormatDateTimeToTime_CN(java.util.Date currDate) {
        return getFormatDateTime_CN(getFormatDateTime_CN(currDate));
    }

    public static Date getFormatDateTime_CN(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    public static String getFormatDateTime(java.util.Date currDate,
        String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
            return dtFormatdB.format(currDate);
        }
    }

    public static Date getFormatDate(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public static Date getFormatDateTime(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public static String getCurrDateStr() {
        return getFormatDate(getCurrDate());
    }

    public static String getCurrDateTimeStr() {
        return getFormatDateTime(getCurrDate());
    }

    public static String getCurrDateStr_CN() {
        return getFormatDate(getCurrDate(), DATE_FORMAT_CN);
    }

    public static String getCurrDateTimeStr_CN() {
        return getFormatDateTime(getCurrDate(), TIME_FORMAT_CN);
    }

    public static Date getDateBeforeOrAfter(int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    public static String getFormatMonth(java.util.Date currDate) {
        return getFormatDate(currDate, MONTH_FORMAT);
    }

    public static String getFormatDay(java.util.Date currDate) {
        return getFormatDate(currDate, DAY_FORMAT);
    }

    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    public static String getFirstDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    public static Date getDateBeforeOrAfterHours(Date curDate, int iHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.HOUR_OF_DAY, iHour);
        return cal.getTime();
    }

    public static int getIntervalSeconds(Date endDate) {
        return getIntervalSeconds(endDate, new Date());
    }

    public static int getIntervalSeconds(Date endDate, Date startDate) {
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        return (int) ((end.getTimeInMillis() - start.getTimeInMillis()) / 1000);
    }

    public static String getMinuteFromSeconds(int seconds) {
        if (seconds == 0) {
            return "0��";
        } else {
            int minute = seconds / 60;
            int second = seconds % 60;
            if (minute > 0)
                return minute + "��" + second + "��";
            else
                return second + "��";
        }
    }

    public static int getDiffDaysFrom2Date(Date begin, Date end) {
        Date d_beign = getFormatDate(getFormatDate(begin));
        Date d_end = getFormatDate(getFormatDate(end));

        int days = 0;
        Calendar c_b = Calendar.getInstance();
        Calendar c_e = Calendar.getInstance();

        c_b.setTime(d_beign);
        c_e.setTime(d_end);

        while (c_b.before(c_e)) {
            days++;
            c_b.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;
    }
    
    public static String getDiffTimeFrom2Date(Date begin, Date end) {
        
    	
        long diff = end.getTime() - begin.getTime();
        if (diff <= 0) {
			return "0天";
		}
        long diffMin = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return Long.toString(diffDays)+"天"+Long.toString(diffHours)+"小时"+Long.toString(diffMin)+"分钟";
    }
    
public static String getDiffTimeFrom2DateIndex(Date begin, Date end) {
        
    	
        long diff = end.getTime() - begin.getTime();
        if (diff <= 0) {
			return "15秒";
		}
        long diffSECOND = diff / 1000;
        long diffMin = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long difMis = diff / 1000 % 60;
        String returnStr = "";
        if (diffDays>0) {
        	returnStr = Long.toString(diffDays)+"天";;
		}
        if (diffHours>0) {
        	returnStr = returnStr+Long.toString(diffHours)+"小时";
		}
        if (diffMin>0) {
        	returnStr = returnStr+Long.toString(diffMin)+"分钟";
		}
        
        if (diffMin == 0 && diffHours == 0 && diffDays == 0) {
			returnStr = Long.toString(difMis)+"秒";
		}
        return returnStr;
    }

    
    public static List<String> getDiffTimeFrom2DateList(Date begin, Date end) {
    	List<String> list = new ArrayList<String>();
        long diff = end.getTime() - begin.getTime();
        if (diff <= 0) {
        	list.add("00");
        	list.add("00");
        	list.add("00");
			return list;
		}
        long diffMin = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        list.add(Long.toString(diffDays));
        list.add(Long.toString(diffHours));
        list.add(Long.toString(diffMin));
        
        return list;
    }

    public static void main(String[] args) {
        Date date1 = DateTimeUtil.getDateBeforeOrAfter(getFormatDate(
            "2012-10-25 20:47:50", DATE_FORMAT_HOUR), 1);
        Date date2 = DateTimeUtil.getFormatDate(DateTimeUtil.getFormatDate(
            new Date(), DateTimeUtil.DATE_FORMAT));
        System.out.println(getDiffDaysFrom2Date(date1, date2));
    }
}
