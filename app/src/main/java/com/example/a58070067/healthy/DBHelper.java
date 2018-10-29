package com.example.a58070067.healthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    public DBHelper(Context context) {
        super(context, "database.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SLEEP_TABLE = "CREATE TABLE sleep ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, sleep_time TEXT, wake_up_time TEXT, user_id TEXT)";

        db.execSQL(CREATE_SLEEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_SLEEP_TABLE = "DROP TABLE IF EXISTS sleep";

        db.execSQL(DROP_SLEEP_TABLE);

        onCreate(db);
    }

    public ArrayList<Sleep> getFriendList(String user_id) {
        ArrayList<Sleep> sleeps = new ArrayList<Sleep>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT date, sleep_time, wake_up_time FROM sleep WHERE user_id = \"" + user_id +
                        "\"",null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {
            Sleep sleep = new Sleep(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2));
            sleeps.add(sleep);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return sleeps;
    }

    public void addFriend(Sleep sleep,String user_id) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(Friend.Column.ID, friend.getId());
        values.put("date", sleep.getDate());
        values.put("sleep_time", sleep.getSleep_time());
        values.put("wake_up_time", sleep.getWake_up_time());
        values.put("user_id", user_id);

        sqLiteDatabase.insert("sleep", null, values);

        sqLiteDatabase.close();
    }
}
