package com.example.project_gym_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserRepository {

    private SQLiteDatabase db;

    public UserRepository(Context context) {
        db = DatabaseManager.getInstance(context).getDatabase();
    }

    public void insertUser(String name, String email) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        db.insert("users", null, values);
    }

    public Cursor getUserById(int id) {
        return db.query("users", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
    }

}
