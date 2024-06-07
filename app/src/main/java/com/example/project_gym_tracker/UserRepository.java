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

    public void insertUser(String name, String email, String password) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("pwd", password);
        db.insert("users", null, values);
    }

    public Cursor getUserById(int id) {
        return db.query("users", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
    }

    public boolean isValidUser(String email) {
        Cursor cursor = null;
        try {
            cursor = db.query("users", null, "email = ?", new String[]{email}, null, null, null);
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public boolean isCorrectPwd(String username, String pwd) {
        Cursor cursor = null;
        try {
            cursor = db.query("users", null, "name = ? AND pwd = ?", new String[]{username, pwd}, null, null, null);
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
