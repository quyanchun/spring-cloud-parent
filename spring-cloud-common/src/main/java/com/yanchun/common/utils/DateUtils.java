package com.yanchun.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	
	private static final String []DAY_OF_WEEK = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	
	private static final int []DAY_OF_WEEK_INT = {7, 1, 2, 3, 4, 5, 6};
	
    public static DayCompare dayComparePrecise(Date fromDate, Date toDate){
        Calendar from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        DayCompare dayCompare = new DayCompare(year,month,day);
        return dayCompare;
    }
    
    /**
	 * 毫秒转时分秒
	 * @param ms
	 * @return
	 */
	public static String msToTime(long ms) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		return formatter.format(ms);
	}
	
	/**
	 * 创建日期格式
	 * @return
	 */
	public static SimpleDateFormat createDateFormat() {
		
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	
	/**
	 * 创建日期时间格式
	 * @return
	 */
	public static SimpleDateFormat createDateTimeFormat() {
		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 获取本周一日期
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek(Date date) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = createDateFormat();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return df.format(cal.getTime()) ;
	}
	
	
	/**
	 * 获取本周天日期
	 * @param date
	 * @return
	 */
	public static String getSundayOfWeek(Date date) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = createDateFormat();
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return df.format(cal.getTime()) ;
	}
	
	/**
	 * 获取周几--
	 * @param calendar
	 * @return
	 */
	public static String getDayOfWeek(Calendar calendar) {
		if(calendar==null) {
			calendar = Calendar.getInstance();
		}
		return DAY_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * 获取周几
	 * @param calendar
	 * @return
	 */
	public static int getIndexDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return DAY_OF_WEEK_INT[calendar.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * 获取周几
	 * @param calendar
	 * @return
	 */
	public static int getIndexDayOfWeek(Calendar calendar) {
		return DAY_OF_WEEK_INT[calendar.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * 获取今天周几
	 * @return
	 */
	public static String getDayOfWeek() {
		
		return getDayOfWeek(null);
	}
	
}
