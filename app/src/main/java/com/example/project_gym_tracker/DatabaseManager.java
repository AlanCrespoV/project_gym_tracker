package com.example.project_gym_tracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private static DatabaseManager instance;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context.getApplicationContext());
        }
        return instance;
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }
}
