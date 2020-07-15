package com.example.asyntaskexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "UserInfo";
    private static String TABLE_NAME = "userInfo";
    private static Integer DB_VERSION = 1;
    private static String COLUMN_ID = "id";
    private static String US_TITLE = "title";
    private static String US_TASK = "task";
    private static String US_DEADLINE = "detail";
    SQLiteDatabase db;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + US_TITLE + " TEXT,"
                        + US_TASK + " TEXT,"
                        + US_DEADLINE + " TEXT"
                        + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(String title, String task, String detail) {
        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(US_TITLE, title);
        cv.put(US_TASK, task);
        cv.put(US_DEADLINE, detail);
        long id = db.insert(TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    public ArrayList<UserInfo> show() {
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{US_TITLE, US_TASK, US_DEADLINE},
                null, null, null, null, null);
        int firstIndex = c.getColumnIndex(US_TITLE);
        int secondIndex = c.getColumnIndex(US_DEADLINE);
        int thirdIndex = c.getColumnIndex(US_TASK);

        ArrayList<UserInfo> listData = new ArrayList<>();
        UserInfo data;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String mTitle = c.getString(firstIndex);
            String mDetail = c.getString(secondIndex);
            String mTask = c.getString(thirdIndex);

            data = new UserInfo(mTitle, mTask, mDetail);
            listData.add(data);
        }
        return listData;
    }

    public ArrayList<UserInfo> getTitle() {
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{US_TITLE},
                null, null, null, null, null);
        int firstIndex = c.getColumnIndex(US_TITLE);
        ArrayList<UserInfo> listData = new ArrayList<>();
        UserInfo data;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String mTitle = c.getString(firstIndex);
            data = new UserInfo(mTitle);
            listData.add(data);
        }
        return listData;
    }

    public String getRecord() {
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{US_TITLE, US_TASK, US_DEADLINE},
                null, null, null, null, null);
        String result = "";
        if (c != null && c.moveToFirst()) {
            int firstIndex = c.getColumnIndex(US_TITLE);
            int secondIndex = c.getColumnIndex(US_TASK);
            int thirdIndex = c.getColumnIndex(US_DEADLINE);
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                String mTitle = c.getString(firstIndex);
                String mTask = c.getString(secondIndex);
                String mDeadline = c.getString(thirdIndex);
                result = "Title: " + mTitle + "\n\n" + "Task: " + mTask +
                        "\n\n" + "Deadline: " + mDeadline + "\n\n";
            }
            return result;
        }
        return result;
    }
}
