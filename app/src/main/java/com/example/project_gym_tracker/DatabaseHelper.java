package com.example.project_gym_tracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gym_tracker.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, email TEXT, pwd TEXT)");
        // Insertar datos iniciales
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('Alice', 'alice@example.com', 'PWD')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('Bob', 'bob@example.com', 'PWD')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('Charlie', 'charlie@example.com', 'PWD')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('David', 'david@example.com', 'PWD')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('Eve', 'eve@example.com', 'PWD')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
