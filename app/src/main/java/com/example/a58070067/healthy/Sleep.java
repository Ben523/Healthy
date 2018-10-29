package com.example.a58070067.healthy;

public class Sleep {

    String date;
    String sleep_time;
    String wake_up_time;

    public Sleep(){}
    public Sleep(String date, String sleep_time, String wake_up_time){
        this.date = date;
        this.sleep_time = sleep_time;
        this.wake_up_time = wake_up_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSleep_time() {
        return sleep_time;
    }

    public void setSleep_time(String sleep_time) {
        this.sleep_time = sleep_time;
    }

    public String getWake_up_time() {
        return wake_up_time;
    }

    public void setWake_up_time(String wake_up_time) {
        this.wake_up_time = wake_up_time;
    }


}
