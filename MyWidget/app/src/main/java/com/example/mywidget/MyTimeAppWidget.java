package com.example.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Implementation of App Widget functionality.
 */
public class MyTimeAppWidget extends AppWidgetProvider{

     static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //Retrieve the time//
        String timeStringHHmm = TimeConvert.getCurrentDateTimeHHmmss();
        String timeStringddMMyyyy = TimeConvert.getCurrentDateTimeddMMyyyy();
        //Lấy dữ liệu ngày được chọn tính đến 00:00 dưới dạng long;
         String timeStringDateSelect = TimeConvert.date_select;

         //
         String time_remaining = null;
         String notification = null;

         if(timeStringDateSelect != null){
             long l_date_select = TimeConvert.convertDateStringToLongDate_Select(TimeConvert.date_select);
             //Lấy dữ liệu ngày được chọn tính đến 09:00 dưới dạng long;
             l_date_select += 9 * 3600 * 1000;
             //Lấy dự liệu tại thời điểm hiện tại dưới dạng long;
             long l_date_current = TimeConvert.convertDateStringToLongDate(TimeConvert.getCurrentDateTime());
             //Khoảng cách gửi hai thời điểm
             long delta_milliseconds = l_date_select - l_date_current;
             System.out.println("delta_microseconds" + delta_milliseconds);
             if(delta_milliseconds > 0){
                 notification = "This event has not happened yet !!!";
                 long delta_house = TimeUnit.MILLISECONDS.toHours(delta_milliseconds);
                 delta_milliseconds -= delta_house*3600*10000;
                 long delta_minutes = TimeUnit.MILLISECONDS.toMinutes(delta_milliseconds);
                 delta_milliseconds -= delta_minutes*60*1000;
                 long delta_seconds = TimeUnit.MILLISECONDS.toSeconds(delta_milliseconds);
                 time_remaining = delta_house + ":" + delta_minutes;
                 //time_remaining = delta_house + ":" + delta_minutes + " : " + delta_seconds;

             }else {
                 notification = "This event has already taken place !!";
             }
         }
         else{
             Toast.makeText(context, "You have not selected an event time !!!", Toast.LENGTH_SHORT).show();
         }




        /*convert string(timeStringddMMyyyy) to charSequence(widgetTextHHmmss)*/
        CharSequence widgetTextHHmmss = timeStringHHmm;

        /*convert string(timeStringddMMyyyy) to charSequence(widgetTextddMMyyyy)*/
        CharSequence widgetTextddMMyyyy = timeStringddMMyyyy;

        //convert string() to charSequence()
         CharSequence widgetTextTimeRemaining = time_remaining;

         //convert string() to charSequence()
         CharSequence widgetTextDateSelect = timeStringDateSelect;


        //Construct the RemoteViews object//
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_time_app_widget);
        //views.setTextViewText(R.id.id_value, String.valueOf(appWidgetId));

//Retrieve and display the time//

        views.setTextViewText(R.id.tv_HHmmss, context.getResources().getString(R.string.time, timeStringHHmm));
        views.setTextViewText(R.id.tv_ddMMyyyy,widgetTextddMMyyyy);
        views.setTextViewText(R.id.tv_time_remaining, widgetTextTimeRemaining);
        views.setTextViewText(R.id.tv_date_select_widget, widgetTextDateSelect);
        views.setTextViewText(R.id.tv_notification, notification);


        Intent intentUpdate = new Intent(context, MyTimeAppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        PendingIntent pendingUpdate = PendingIntent.getBroadcast(
                context, appWidgetId, intentUpdate,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.tv_HHmmss, pendingUpdate);
        views.setOnClickPendingIntent(R.id.tv_time_remaining,pendingUpdate);

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://code.tutsplus.com/"));
        //AppCompatActivity appCompatActivity = new AppCompatActivity(R.layout.my_time_app_widget);
        Intent intentEvent = new Intent(context, Event.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentEvent, 0);
        views.setOnClickPendingIntent(R.id.ll_mywidget, pendingIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for(int appWidgetId : appWidgetIds){
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(context, "Widget has been updated! ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

