package cn.itcast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转字符串
     * @param date
     * @param formatStr
     * @return
     */
    public static String date2String(Date date, String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

}
