package com.example.a58070067.healthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "database.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SLEEP_TABLE = "CREATE TABLE sleep ( id TEXT PRIMARY KEY AUTOINCREMENT, " +
                "sleep_time TEXT, wake_up_time TEXT)";

        db.execSQL(CREATE_SLEEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_SLEEP_TABLE = "DROP TABLE IF EXISTS sleep";

        db.execSQL(DROP_SLEEP_TABLE);

        onCreate(db);
    }
}
