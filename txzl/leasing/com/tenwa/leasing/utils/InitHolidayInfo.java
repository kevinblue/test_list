package com.tenwa.leasing.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitHolidayInfo {
	
	public static Map<String,String> getHoliday(String year)throws Exception {
		 Map<String,String>holiday=new HashMap<String,String>();
		 Calendar curY=Calendar.getInstance();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //java.util.Date start = sdf.parse(curY.get(Calendar.YEAR)+"-01-01");//开始时间
        //java.util.Date end = sdf.parse(curY.get(Calendar.YEAR)+"-12-31");//结束时间
		 java.util.Date start = sdf.parse(year+"-01-01");//开始时间
        java.util.Date end = sdf.parse(year+"-12-31");//结束时间
        List<Date> lists = dateSplit(start, end);
        if (!lists.isEmpty()) {
            for (Date date : lists) {
         	   Calendar cal = Calendar.getInstance();
         	    cal.setTime(date);
                if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
                {
               	 holiday.put(sdf.format(date),getWorkDay(date,-1));
                }
                if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
                {
               	 holiday.put(sdf.format(date),getWorkDay(date,-2));
                }
        }
        }
		 return holiday;
	 }
	 private static List<Date> dateSplit(java.util.Date start, Date end)throws Exception {
		    if (!start.before(end))
		        throw new Exception("开始时间应该在结束时间之后");
		    Long spi = end.getTime() - start.getTime();
		    Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

		    List<Date> dateList = new ArrayList<Date>();
		    dateList.add(end);   
		    for (int i = 1; i <= step; i++) {
		        dateList.add(new Date(dateList.get(i - 1).getTime()
		                - (24 * 60 * 60 * 1000)));// 比上一天减一
		    }
		    return dateList;
	}
	private static String getWorkDay(java.util.Date cdate ,int dual) throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(cdate); 
	     calendar.add(calendar.DATE,dual);//把日期往后增加一天.整数往后推,负数往前移动 
	     cdate=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		return sdf.format(cdate);
	}
	 public static void main(String[] args){
		 try {
			InitHolidayInfo.getHoliday("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
