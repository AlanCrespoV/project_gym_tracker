package com.example.project_gym_tracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gym_tracker.db";
    private static final int DATABASE_VERSION = 2; // Incrementa la versión para la actualización
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating database and inserting initial data...");
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, email TEXT, pwd TEXT)");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('GymBeast', 'gym_beast666@example.com', 'password123')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('Pedro', 'pedro6_9@example.com', 'password123')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('yoo', 'CEO_of_SEX@example.com', 'password123')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('idk', 'idk@example.com', 'password123')");
        db.execSQL("INSERT INTO users (name, email, pwd) VALUES ('userLol', 'lol@example.com', 'password123')");

        db.execSQL("CREATE TABLE routines (id INTEGER PRIMARY KEY, name TEXT, username TEXT)");
        db.execSQL("CREATE TABLE exercises (id INTEGER PRIMARY KEY, routine_id INTEGER, name TEXT, muscle_group TEXT, reps INTEGER, sets INTEGER, weight REAL, FOREIGN KEY(routine_id) REFERENCES routines(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE routines ADD COLUMN username TEXT");
        }
    }
}
