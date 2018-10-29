package com.example.a58070067.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        _wake_up_time.setText(String.valueOf(_row.getWake_up_time()));
        String sleep_t = String.valueOf(_row.getSleep_time());
        SimpleDateFormat formatter5 =new SimpleDateFormat("HH:mm");
        //Date date1= formatter5.parse(sleep_t);

        return _weightItem;
    }
}
