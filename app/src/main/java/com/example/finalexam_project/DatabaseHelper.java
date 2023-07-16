package com.example.finalexam_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.Vector;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notification_history.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE notifications (title TEXT, body TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades if needed
    }


    public void insertNotification(NotificationModel notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", notification.title);
        values.put("body", notification.body);
        db.insert("notifications", null, values);
        db.close();
    }

    public Vector<NotificationModel> getAllNotifications() {
        Vector<NotificationModel> notificationList = new Vector<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notifications", null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            notificationList.add(new NotificationModel(
                    cursor.getString(0),
                    cursor.getString(1)
            ));
            cursor.move(1);
        }
        db.close();

        return notificationList;
    }




}