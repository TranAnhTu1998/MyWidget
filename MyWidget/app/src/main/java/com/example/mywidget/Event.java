package com.example.mywidget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Event extends AppCompatActivity {
    //Khái báo biến calendar để tham chiếu đến CalendarView;
    private CalendarView cldv_calendar;
    //Khái báo biến tv_select để tham chiếu đến TextView Select;
    private TextView tv_data_select;
    //Khái báo biến bnt_select để tham chiếu đến Button Select;
    private Button bnt_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        tv_data_select = (TextView)findViewById(R.id.tv_date_select_calendar);

        cldv_calendar = findViewById(R.id.clv_calendar);
        cldv_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //long l_date = calendarView.getDate();
                //
                String str_i2 = new Integer(i2).toString();
                if(1<= i2 && i2 <= 9){
                    str_i2 = "0"+str_i2;
                }
                //
                i1 = i1 + 1;
                String str_i1 = new  Integer(i1).toString();
                if(1<= i1 && i1 <= 9){
                    str_i1 = "0"+str_i1;
                }
                String str_date = str_i2 + "/" + str_i1 + "/" + i;
                tv_data_select.setText(str_date);
            }
        });

        //Sử lý sự kiện khi ấn nút button Select;
        bnt_select = (Button)findViewById(R.id.btn_select);
        bnt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lưu dữ liệu tên bộ nhớ chung.
                String str_data_select = tv_data_select.getText().toString();
                TimeConvert.date_select = str_data_select;
                Toast.makeText(getApplicationContext(), "The event " + str_data_select + "has been saved !!! ", Toast.LENGTH_SHORT).show();
                //Exit Activity.
                finish();
            }
        });


    }
}
