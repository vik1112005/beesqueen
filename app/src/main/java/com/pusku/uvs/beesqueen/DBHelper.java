package com.pusku.uvs.beesqueen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 26.06.2016.
 */
class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";
    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "labels", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table labels ("
                + "id integer primary key autoincrement,"
                + "nameoflabel text,"
                + "dateoflabel text" + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
