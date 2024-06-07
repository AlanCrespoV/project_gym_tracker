package com.example.project_gym_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoutinesRepository {
    private SQLiteDatabase db;

    public RoutinesRepository(Context context) {
        db = DatabaseManager.getInstance(context).getDatabase();
    }

    public void insertRoutine(Routine routine, String username) {
        ContentValues values = new ContentValues();
        values.put("name", routine.getName());
        values.put("username", username);
        long routineId = db.insert("routines", null, values);

        for (Exercise exercise : routine.getExercises()) {
            insertExercise(routineId, exercise);
        }
    }

    public void insertExercise(long routineId, Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put("routine_id", routineId);
        values.put("name", exercise.getName());
        values.put("muscle_group", exercise.getMuscleFocus());
        values.put("reps", exercise.getRepetitions());
        values.put("sets", exercise.getSets());
        values.put("weight", exercise.getWeight());
        db.insert("exercises", null, values);
    }

    public void updateExercise(int exerciseId, int reps, int sets, double weight) {
        ContentValues values = new ContentValues();
        values.put("reps", reps);
        values.put("sets", sets);
        values.put("weight", weight);
        db.update("exercises", values, "id = ?", new String[]{String.valueOf(exerciseId)});
    }

    public List<Routine> getRoutinesByUser(String username) {
        List<Routine> routines = new ArrayList<>();
        Cursor cursor = db.query("routines", null, "username = ?", new String[]{username}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                List<Exercise> exercises = getExercisesByRoutineId(id);
                routines.add(new Routine(name, exercises));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return routines;
    }

    public List<Exercise> getExercisesByRoutineId(int routineId) {
        List<Exercise> exercises = new ArrayList<>();
        Cursor cursor = db.query("exercises", null, "routine_id = ?", new String[]{String.valueOf(routineId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String muscleGroup = cursor.getString(cursor.getColumnIndexOrThrow("muscle_group"));
                int reps = cursor.getInt(cursor.getColumnIndexOrThrow("reps"));
                int sets = cursor.getInt(cursor.getColumnIndexOrThrow("sets"));
                double weight = cursor.getDouble(cursor.getColumnIndexOrThrow("weight"));
                exercises.add(new Exercise(name, muscleGroup, reps, sets, weight));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return exercises;
    }
}
