/**
 *
 *author：wangyulin
 *16130120138
 *mail:1009948721@qq.com
 */
package www.wyl.the_second;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;


public class Cal{
    public Cal(String year, String month,String day)throws ParseException {
        this.year =Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.day = Integer.parseInt(day);
        this.time = DateTools.stringToDate(year,month,day);
        this.weekDay = DateTools.getDaysOfMonth(this.time);
    }
    public static int returnDateOfMonth(String month,String year)throws Exception{
        return DateTools.getDaysOfMonth(DateTools.stringToDate(year,month,"1"));
    }
    private int year;
    private int month;
    private int weekDay;
    private int day;
    private Date time;
    public static void main(String[] args) throws ParseException{

             Cal tes = new Cal(args[1],args[0],"1");
             if(tes.month>12||tes.month<1||tes.year<1970){
                 System.out.println("Error:you are tring to input a time that does not exist!");
                 System.out.println("ATTENTION:we will try to show nowtime to you!");
                tes.initdailyPrint();
                return;
             }
             DateTools.getNowDateShort();
            tes.dailyPrint();


    }
    //错误输入处理
    public void initdailyPrint() throws ParseException{
        int[] nowTime=DateTools.getNowDateShort();
        this.year = nowTime[0];
        this.month = nowTime[1];
        this.day = nowTime[2];
        this.time = DateTools.stringToDate(String.valueOf(this.year),String.valueOf(this.month),String.valueOf(this.day));
        this.weekDay = DateTools.getDaysOfMonth(this.time);
        dailyPrint();

    }
    //打印日历
    public void dailyPrint()throws ParseException{
        String dayString =DateTools.dateToWeek(DateTools.dateToString(this.time));
        int day = DateTools.weekStringtoInt(dayString);
       // System.out.print(day);
       // System.out.print(dayString);
        System.out.printf("%s",DateTools.monthToString(this.month)+" ");
        System.out.println(year);
        int monthDays = DateTools.getDaysOfMonth(this.time);
        int Status =0;
        int row = 0;
            for(int i=0;i<7;i++){
                System.out.printf("%4s",DateTools.weekDays[i]);
            }
            for(int j=1;j<=monthDays;j++){
                if(Status==0)
                {
                   // System.out.print("打印开始空格");
                    System.out.print("\n");
                    for(int i= 0;i<day;i++){
                       System.out.print(" "+" "+" "+" ");//
                    }
                    Status=1;
                    row = day;
                }
                if(row==7){
                    System.out.print("\n");
                    row = 0;
                }
                System.out.printf("%4d",j);
                row=row+1;
            }


    }

}

//工具类
class DateTools {

    public static String[] weekDays = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
 //输入星期几两位字母简写，返回整数类型
    public static int weekStringtoInt(String day){
        if("Su"==day)
            return 0;
        if("Mo"==day)
            return 1;
        if("Tu"==day)
            return 2;
        if("We"==day)
            return 3;
        if("Th"==day)
            return 4;
        if("Fr"==day)
            return 5;
        if("Sa"==day)
            return 6;
        else
            return -1;
    }
//返回一个存储当前时间的年月日的整型数组
    public static int[] getNowDateShort() {
        Calendar c= Calendar.getInstance();
        int[] now={c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)};
        return now;

    }

    //返回指定日期星期几
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //String[] weekDays = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return DateTools.weekDays[w];
    }
//返回给定月份的英文名
    public static String monthToString(int month){
        String[] monthToEnglish = {"January","February","March","April","May","June","July","August",
        "September","October","November","December"};
        return monthToEnglish[month-1];
    }
//返回一个月拥有多少天
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
//输入年份和月份，返回一个当年当月一号的时间类型参数
    public static Date stringToDate(String year,String month,String day)throws ParseException{
        String time =year+"-"+month+"-"+day;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(time);

        return date;
    }
    //输入一个时间类型参数，返回一个时间的字符串
    public static String dateToString(Date time)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

}