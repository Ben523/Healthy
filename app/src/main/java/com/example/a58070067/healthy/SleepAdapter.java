package com.example.a58070067.healthy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepAdapter extends ArrayAdapter<Sleep> {
    List<Sleep> sleeps = new ArrayList<Sleep>();
    Context context;
    private String date;
    private String sleepTime;
    private String wakeupTime;
    private View view;

    public SleepAdapter(Context context, int resouce, List<Sleep> objects){
        super(context, resouce, objects);
        this.sleeps = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View _weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_custom_listview,
                parent,
                false);
        view = _weightItem;
        TextView _date = _weightItem.findViewById(R.id.custom_date);
        TextView _sleep_time = _weightItem.findViewById(R.id.custom_weight);
        TextView _wake_up_time = _weightItem.findViewById(R.id.custom_status);



        Sleep _row = sleeps.get(position);
        _date.setText(_row.getDate());
        date = _row.getDate();
        sleepTime = String.valueOf( _row.getSleep_time() );
        wakeupTime = String.valueOf(_row.getWake_up_time());
        _sleep_time.setText(String.valueOf( _row.getSleep_time() )+" - "+String.valueOf(_row.getWake_up_time()));
        String time1 = String.valueOf(_row.getSleep_time());
        String time2 = String.valueOf(_row.getWake_up_time());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");




//            Date date1 = format.parse(time1);
//            Date date2 = format.parse(time2);
//            long difference = (date1.getTime()/ (60 * 60 * 1000))-(date2.getTime()/ (60 * 60 * 1000));
//            long diffMinutes = difference / (60 * 1000) % 60;
//            long diffHours = difference / (60 * 60 * 1000) % 24;

            String[] fractions1=time1.split(":");
            String[] fractions2=time2.split(":");
            fractions1[0] = fractions1[0].replaceAll("\\s+","");
            fractions1[1] = fractions1[1].replaceAll("\\s+","");
            fractions2[0] = fractions2[0].replaceAll("\\s+","");
            fractions2[1] = fractions2[1].replaceAll("\\s+","");
            int hours1=Integer.parseInt(fractions1[0]);
            int hours2=Integer.parseInt(fractions2[0]);
            int minutes1=Integer.parseInt(fractions1[1]);
            int minutes2=Integer.parseInt(fractions2[1]);
            if(hours1>12)
            {
                hours1 = 24%hours1;
            }
            int hourDiff=hours1+hours2;
            int minutesDiff=minutes1-minutes2;
            if (minutesDiff < 0) {
                minutesDiff = 60 + minutesDiff;
                hourDiff--;
            }
            if (hourDiff < 0) {
                hourDiff = 24 + hourDiff ;
            }
            if(minutesDiff < 10)
            {
                _wake_up_time.setText( hourDiff + ":0" + minutesDiff);
            }
            else
            {
                _wake_up_time.setText( hourDiff + ":" + minutesDiff);
            }







        return _weightItem;
    }


}
