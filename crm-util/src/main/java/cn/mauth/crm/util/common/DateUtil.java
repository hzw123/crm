package cn.mauth.crm.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DAY="yyyyMMdd";
    public static final String SQL_DAY="%Y%m%d";

    private static final String WEEK="w";
    public static final String SQL_WEEK="%Y%u";

    private static final String MONTH="yyyyMM";
    public static final String SQL_MONTH="%Y%m";

    private static final String YEAR="yyyy";
    public static final String SQL_YEAR="%Y";


    public static String toDay(Date date){
        return DateUtil.format(DAY).format(date);
    }

    public static String toWeek(Date date){
        String week=DateUtil.setWeek(DateUtil.format(WEEK).format(date));
        return DateUtil.format(YEAR).format(date)+week;
    }

    private static String setWeek(String week){
        if(week.length()==1)
            week=0+week;
        return week;
    }

    public static String getDate(){
        return DateUtil.toDay(new Date());
    }

    public static String toMonth(Date date){
        return DateUtil.format(MONTH).format(date);
    }

    public static String toYear(Date date){
        return DateUtil.format(YEAR).format(date);
    }

    public static SimpleDateFormat format(String format){
        return new SimpleDateFormat(format);
    }

}
