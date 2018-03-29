package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String DEFAULT_LONGDATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss SSS
	 */
	public static String DEFAULT_LONGDATE_WITH_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
	
	/**
	 * yyyyMMddHHmmss
	 */
	public static String DEFAULT_LONGDATE_FORMAT_UNSIGNED = "yyyyMMddHHmmss";


	/**
	 * yyyy-MM-dd
	 */
	public static String DEFAULT_SHORTDATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * YYYY.MM.dd
	 */
	public static String DEFAULT_SPOTDATA_FORMAT = "YYYY.MM.dd";
	
	/**
	 * yyyyMMdd
	 */
	public static String SHORT_DATE_FORMAT = "yyyyMMdd";
	
	/**
	 * 初始化指定格式 yyyy-MM-dd HH:mm:ss SimpleDateFormat实例
	 */
	public static ThreadLocal<SimpleDateFormat> DEFAULT_LONGDATE_FORMAT_DF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_LONGDATE_FORMAT);
        }
    };
    
    /**
	 * 初始化指定格式 yyyy-MM-dd HH:mm:ss SSS SimpleDateFormat实例
	 */
	public static ThreadLocal<SimpleDateFormat> DEFAULT_LONGDATE_WITH_SECOND_FORMAT_DF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_LONGDATE_WITH_SECOND_FORMAT);
        }
    };
    
    /**
	 * 初始化指定格式 yyyyMMddHHmmss SimpleDateFormat实例
	 */
    public static ThreadLocal<SimpleDateFormat> DEFAULT_LONGDATE_FORMAT_DF_UNSIGNED = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_LONGDATE_FORMAT_UNSIGNED);
        }
    };
    
    /**
	 * 初始化指定格式 yyyy-MM-dd SimpleDateFormat实例
	 */
    public static ThreadLocal<SimpleDateFormat> DEFAULT_SHORTDATE_FORMAT_DF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_SHORTDATE_FORMAT);
        }
    };
    
    /**
   	 * 初始化指定格式 DEFAULT_SPOTDATA_FORMAT SimpleDateFormat实例
   	 */
    public static ThreadLocal<SimpleDateFormat> DEFAULT_SPOTDATA_FORMAT_DF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_SPOTDATA_FORMAT);
        }
    };
    
    /**
	 * 初始化指定格式 yyyyMMdd SimpleDateFormat实例
	 */
	public static ThreadLocal<SimpleDateFormat> SHORT_DATE_FORMAT_DF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(SHORT_DATE_FORMAT);
        }
    };
    

	/**
	 * 计算2个日期相差的绝对天数 可能大于0 可能小于0
	 * 
	 * @param beginDate 开始日期
	 * @param endDate   结束日期
	 * @return 相差天数
	 */
	public static Long getDaysBetween(Date beginDate, Date endDate) {
		try {
			beginDate = DEFAULT_SHORTDATE_FORMAT_DF.get().parse(DEFAULT_SHORTDATE_FORMAT_DF.get().format(beginDate));
			endDate = DEFAULT_SHORTDATE_FORMAT_DF.get().parse(DEFAULT_SHORTDATE_FORMAT_DF.get().format(endDate));
			return (beginDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			logger.error("格式化时间失败", e);
			return null;
		}
	}

	/**
	 * 计算一个date 添加 month 个月后得到的 date 结果
	 * 
	 * @param date
	 * @param month
	 *            要添加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + month);
		return c.getTime();
	}
	
	
	/**
	 * 计算一个date 添加 day 天后得到的 date 结果
	 * 
	 * @param date
	 * @param day
	 *            要添加的天数
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
		return c.getTime();
	}

	/**
	 * 计算一个date 添加 month 月 day 天后得到的 date 结果
	 * @param date
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date addMonth(Date date, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(addMonth(date, month));
		c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
		return c.getTime();
	}


	/**
	 * 遍历得到时间段的时间列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> iterateDate(Date startDate, Date endDate) {
		List<Date> dateList = new ArrayList<Date>();
		try {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(startDate);
			c2.setTime(endDate);
			while (c1.before(c2)) {
				dateList.add(c1.getTime());
				c1.add(Calendar.DATE, 1);
			}
			dateList.add(endDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dateList;
	}

	/**
	 * 得到日期为星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		String result = "";
		switch (weekDay) {
		case Calendar.MONDAY:
			result = "周一";
			break;
		case Calendar.TUESDAY:
			result = "周二";
			break;
		case Calendar.WEDNESDAY:
			result = "周三";
			break;
		case Calendar.THURSDAY:
			result = "周四";
			break;
		case Calendar.FRIDAY:
			result = "周五";
			break;
		case Calendar.SATURDAY:
			result = "周六";
			break;
		case Calendar.SUNDAY:
			result = "周日";
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean equalSameDay(Date date1, Date date2) {
		String strDate1 = getShortDateStr(date1);
		String strDate2 = getShortDateStr(date2);
		if (strDate1.equals(strDate2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * getFormatDate:指定格式指定时间格式化 <br/>  
	 *  
	 * @author gaoshuang  
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date getFormatDate(String str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = new Date();
			d = sdf.parse(str);
			return d;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/** 
	 * 比较两个日期相差的秒数 
	 */
	public static long compareTime(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar c = Calendar.getInstance();

		c.setTime(date1);
		long l1 = c.getTimeInMillis();

		c.setTime(date2);
		long l2 = c.getTimeInMillis();

		return (l2 - l1) / 1000;
	}

	/**
	 * 返回date对象2017-08-18 yyyy-MM-dd
	 * @return Date 2017-08-18
	 */
	public static Date getDate(String date) {
		try {
			return DEFAULT_SHORTDATE_FORMAT_DF.get().parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 获取指定长形Date对象
	 * @param date yyyy-MM-dd hh:mm:ss格式的日期
	 * @return date
	 */
	public static Date getDateLong(String date) {
		try {
			return DEFAULT_LONGDATE_FORMAT_DF.get().parse(date);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 将日期str yyyy-MM-dd转成日期
	 * @param str 日期格式 1991-09-09
	 * @return Date
	 */
	public static Date parseShortDate(String str) {
		try {
			Date d = new Date();
			d = DEFAULT_SHORTDATE_FORMAT_DF.get().parse(str);
			return d;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 *  返回两个日期相差的秒数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static long getTimeOut(String date1, String date2) throws Exception {
		return (SimpleDateFormat.getDateTimeInstance().parse(date2).getTime()
				- SimpleDateFormat.getDateTimeInstance().parse(date1).getTime()) / 1000;
	}

	/**
	 *  检查date是否是当天
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar log = Calendar.getInstance();
		log.setTime(date);
		return (log.before(now) && now.get(now.YEAR) == log.get(log.YEAR)
				&& now.get(now.DAY_OF_YEAR) == log.get(log.DAY_OF_YEAR));
	}

	/**
	 *  比较两个日期相差的月数
	 */
	public static int compareMonth(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return iMonth;
	}

	/**
	 * 字符串时间转换成date
	 * @param str yyyy-MM-dd hh:mm:ss
	 * @return date
	 */
	public static Date parseLongDate(String str) {
		try {
			Date d = new Date();
			d = DEFAULT_LONGDATE_FORMAT_DF.get().parse(str);
			return d;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取系统当前时间
	 * @return 以格式yyyy-MM-dd HH:mm:ss返回当前日期
	 */
	public static String getCurrentDateStr() {
		String ret = "";
		try {
			Calendar cal = Calendar.getInstance();
			ret = DEFAULT_LONGDATE_FORMAT_DF.get().format(cal.getTime());
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;

	}
	
	/**
	 * 获取当前日期时间无符号
	 * @return 以格式yyyyMMddHHmmss返回当前日期
	 */
	public static String getCurrentDate2Str() {
		String ret = "";
		try {
			Calendar cal = Calendar.getInstance();
			ret = DEFAULT_LONGDATE_FORMAT_DF_UNSIGNED.get().format(cal.getTime());
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;

	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd hh:mm:ss格式的日期
	 */
	public static String getDateStr(Date date) {
		String ret = "";
		try {
			ret = DEFAULT_LONGDATE_FORMAT_DF.get().format(date);
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
	}
	
	

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd格式的日期
	 */
	public static String getShortDateStr(Date date) {
		String ret = "";
		try {
			ret = DEFAULT_SHORTDATE_FORMAT_DF.get().format(date);
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
	}
	
	/**
	 * 
	 * 检查date 是否在时间 startDate endDate之间 <br/>  
	 *  
	 * @author gaoshuang  
	 * @param startDate 起始日期
	 * @param endDate   结束日期
	 * @param date      要判断的日期
	 * @return 
	 */
	public static boolean isBetweenTwoDate(Date startDate, Date endDate, Date date) {
		if (startDate.before(date) && endDate.after(date)) {
			return true;
		}
		return false;
	}

	

	/**
	 * 
	 * @return 以yyyyMMdd格式返回服务器当前日期
	 */
	public static String getCurrentDateShortStr() {
		String ret = "";
		try {
			ret = SHORT_DATE_FORMAT_DF.get().format(new Date());
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;

	}
	
	/**
	 * 
	 * 获取中文日期格式 
	 *  
	 * @author gaoshuang  
	 * @param eventDate
	 * @return
	 */
	public static String getShortDateChineseStr(Date eventDate) {
		String[] str = getShortDateStr(eventDate).split("-");

		return str[0] + "年" + str[1] + "月" + str[2] + "日";
	}

	/**
	 * 
	 * 获取date时间格式化后的时间 2017-01-01<br/>  
	 *  
	 * @author gaoshuang  
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
        return format(date, DEFAULT_SHORTDATE_FORMAT);
    }

	/**
	 * 
	 * 指定pattern格式格式化时间
	 *  
	 * @author gaoshuang  
	 * @param date
	 * @param pattern
	 * @return
	 */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
	
	/**
	 * 
	 * @param date
	 * @return M/d日 hh:mm 格式的日期
	 */
	public static String getDateStrWithHMChinese(Date date) {
		String ret = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("M/d HH:mm");
			ret = sdf.format(date);
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
	}
	

	/**
	 * 
	 * @param date
	 * @return HH:mm格式返回小时和分钟
	 */
	public static String getHourMinteStr(Date date) {
		String ret = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			ret = sdf.format(date);
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
	}
	
	/**
	 * 获取DATE 属于的年月
	 * @param date
	 * @return yyyy-MM格式返回小时和分钟
	 */
	public static String getYearMonthStr(Date date) {
		String ret = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			ret = sdf.format(date);
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
	}
	
	/**获取两个时间相差的天数,包含开始时间,包含结束时间;只取日期;
	 * <br><b>算法为: 天数 = (结算日-开始日)+1</b><br>
	 * 如:2014-08-01 12:00:00 到 2014-08-01 12:00:01;也算一天;<br>
	 * 2014-08-01 12:00:00 到 2014-08-02 23:59:59; 就算两天;<br>
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static  int getDifferDays(Date startTime,Date endTime){
		int days = 0;
		try {
			SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd");
			startTime = fdate.parse(fdate.format(startTime));
			endTime = fdate.parse(fdate.format(endTime));
			while(endTime.compareTo(startTime)>=0){
				Calendar ca = Calendar.getInstance();
				ca.setTime(startTime);
				ca.add(Calendar.DATE, 1);
				days++;
				startTime = fdate.parse(fdate.format(ca.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}
	
	/**
	 * 判断两个日期是否为同一天
	 * @param date1
	 * @param date2
	 * @return true 是同一天
	 */
	public static boolean isSameDay(Date date1,Date date2){
		String dateaStr1 = getShortDateStr(date1);
		String dateaStr2 = getShortDateStr(date2);
		if(!BaseUtils.isEmptyObject(dateaStr1)&&dateaStr1.equals(dateaStr2)){
			return true;
		}
		return false;
	}


	/**
	 *
	 * @param n 正数：今天之后n天 负数：今天之前n天
	 * @return
	 */
	public static String getAnyDate(int n) {
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + n);
		return format(c.getTime(), "yyyy-MM-dd");
	}

	/**
	 *
	 * @param n 正数：今天之后n天 负数：今天之前n天
	 * @return
	 */
	public static String getAnyTime(int n) {
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + n);
		return format(c.getTime(), "MM-dd");
	}
	
	/**
	 * 获取当日之后多少天之后凌晨时间
	 * @param n 正数：今天之后n天 负数：今天之前n天
	 * @return
	 */
	public static Date getAnyWeeHours(int n) {
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + n);
		if(c.getTime() != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
				return df.parse(df.format(c.getTime()));
			} catch (ParseException e) {
				return null;
			}
        }
		return null;
	}
	
	/**
	 * 将日期转为  年月日
	 * @param date
	 * @return
	 */
	public static String getDataString(Date date){
	
		if (BaseUtils.isEmptyObject(date)) {
			return "暂无日期";
		}
		String rs = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		
		rs = year+"年"+month+"月"+day+"日";
		return rs;
	}

	/**
	　* @Description: 将日期转为  年 月 日 时分秒
	　* @param date
	　* @author gaoshuang
	　* @date 2018/3/25 13:57
	　* @return 2018年3月25日 13:56:35
	　*/
	public static String getCnDateTimeString(Date date){

		if (BaseUtils.isEmptyObject(date)) {
			return "暂无日期";
		}
		String rs = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);

		rs = year+"年"+month+"月"+day+"日 " + h + ":" + m + ":" + s;
		return rs;
	}
	
	/**
	 * 获取当日时间点时间戳
	 * @param hours 时间点 小时 24小时制
	 * @return
	 */
	public static Date getTodayPointInTime(int hours){
		Calendar c = Calendar.getInstance();      
        c.set(Calendar.HOUR_OF_DAY, hours);  
        c.set(Calendar.MINUTE, 0);  
        c.set(Calendar.SECOND, 0);  
        c.set(Calendar.MILLISECOND, 0);  
        return c.getTime();
	}
	
	/**
	 * 获取当日时间点时间戳
	 * @param hours 时间点 小时 24小时制  minute 分
	 * @return
	 */
	public static Date getTodayPointInTime(int hours, int minute){
		Calendar c = Calendar.getInstance();      
        c.set(Calendar.HOUR_OF_DAY, hours);  
        c.set(Calendar.MINUTE, minute);  
        c.set(Calendar.SECOND, 0);  
        c.set(Calendar.MILLISECOND, 0);  
        return c.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(DEFAULT_LONGDATE_WITH_SECOND_FORMAT_DF.get().format(getTodayPointInTime(23)));
//		System.out.println(isSameDay(new Date(), new Date(new Date().getTime()-10*60*60*1000)));
//		System.out.println(getAnyDate(-1));
//		System.out.println(getAnyDate(-7));
//
//		System.out.println(getAnyTime(-1));
//		System.out.println(getAnyTime(-2));
//		System.out.println(getDaysBetween(new Date(), getFormatDate("2018-02-01 12:00:00","yyyy-MM-dd")));
//		System.out.println(getAnyDate(2));
//		System.out.println(getAnyTime(-3));
//		System.out.println(getAnyTime(-4));
//		System.out.println(getAnyTime(-5));
//		System.out.println(getAnyTime(-6));
//		System.out.println(getAnyTime(-7));
//		System.out.println(getDifferDays(getFormatDate("2014-08-01 12:00:00","yyyy-MM-dd"),getFormatDate("2014-08-01 12:00:01","yyyy-MM-dd")));
		//System.out.println(DEFAULT_LONGDATE_WITH_SECOND_FORMAT_DF.get().format(addDay(new Date(), -7)));
		System.out.println(getAnyTime(1));
	    System.out.println(getDifferDays(getFormatDate("2014-08-01 12:00:00","yyyy-MM-dd"),getFormatDate("2014-08-03 12:00:01","yyyy-MM-dd"))-1);
		System.out.println(getCnDateTimeString(new Date()));
	}
}
