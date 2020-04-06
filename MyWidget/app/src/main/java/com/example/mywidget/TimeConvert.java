package com.example.mywidget;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConvert {

    static String  date_select  = null;

    public static String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date); //2016/11/16 12:08:43
    }

    public static String getCurrentDateTimeHHmmss(){
        DateFormat dateFormat_HHmm = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return  dateFormat_HHmm.format(date);
    }

    public static String getCurrentDateTimeddMMyyyy(){
        DateFormat dateFormat_ddMMyyyy = new SimpleDateFormat("EEE dd/MM/yyyy");
        Date date = new Date();
        return  dateFormat_ddMMyyyy.format(date);
    }

    public static String convertLongdataToStringddMMyyyy(long TimeinMilliSeccond){
        Date date = new Date(TimeinMilliSeccond);
        String string = new SimpleDateFormat("dd/MM/yyyy").format(date);
        return string;
    }

    public static long convertDateStringToLongDate_Select(String date_select){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long l_date_select = -1;
        try {
            Date date = dateFormat.parse(date_select);
            //Calendar calendar = Calendar.getInstance();
            //calendar.setTime(date);
            //l_date_select =  calendar.getTimeInMillis();
            l_date_select = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l_date_select;
    }

    public static long convertDateStringToLongDate(String date_select){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        long l_date_select = -1;
        try {
            Date date = dateFormat.parse(date_select);
            //Calendar calendar = Calendar.getInstance();
            //calendar.setTime(date);
            //l_date_select =  calendar.getTimeInMillis();
            l_date_select = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l_date_select;
    }
}
