package com.example.a58070067.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepAdapter extends ArrayAdapter<Sleep> {
    List<Sleep> sleeps = new ArrayList<Sleep>();
    Context context;

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

        TextView _date = _weightItem.findViewById(R.id.custom_date);
        TextView _sleep_time = _weightItem.findViewById(R.id.custom_weight);
        TextView _wake_up_time = _weightItem.findViewById(R.id.custom_status);

        Sleep _row = sleeps.get(position);
        _date.setText(_row.getDate());
        _sleep_time.setText(String.valueOf( _row.getSleep_time() )+" - "+String.valueOf(_row.getWake_up_time()));
        //_wake_up_time.setText(String.valueOf(_row.getWake_up_time()));
        String sleep_t = String.valueOf(_row.getSleep_time());
        String time1 = "12:00";
        String time2 = "14:00";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            long difference = date2.getTime() - date1.getTime();
            _wake_up_time.setText(Long.toString(difference));
        } catch (ParseException e) {
        }


        return _weightItem;
    }
}
