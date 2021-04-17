package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateutils {

    //日期转化字符串类型
    //patt需要转化的格式
    public static String dateToString(Date date,String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format=sdf.format(date);
        return format;
    }
    //字符串转化日期
    public static Date StringToDate(String str,String patt)throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date parse=sdf.parse(str);
        return parse;
    }
}
