package com.example.service_hi.system_package.util.date;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    public static void main(String[] args) {
        System.out.println(getAlertYear(new Date()));
        List<Date> date = getDateConvert(new Date(),new Date());
        for (Date d:date
             ) {
            System.out.println(d.toString());
        }
    }
    public static void getSetTime(Date startTime, Date endTime){
        if(startTime==null||endTime==null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date end = calendar.getTime();
            System.out.println((sdf.format(new Date(System.currentTimeMillis()))));
            System.out.println(sdf.format(end));
        }
    }
    public static List<Date> getDateConvert(Date startTime, Date endTime){
        List<Date> date = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        date.add(start);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endTime);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        Date end = calendarEnd.getTime();
        date.add(end);
        return date;
    }
    public static Date getAlertYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间  
//        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
//        calendar.add(Calendar.DATE, -1);//当前时间前去一个月，即一天前的时间
        Date dataAlert = calendar.getTime();
        return dataAlert;
    }
}
