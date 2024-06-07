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

    void insertExercise(long routineId, Exercise exercise) {
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

    public Routine getRoutineById(int routineId) {
        Cursor cursor = db.query("routines", null, "id = ?", new String[]{String.valueOf(routineId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            List<Exercise> exercises = getExercisesByRoutineId(routineId);
            cursor.close();
            return new Routine(routineId, name, exercises, username);
        }
        return null;
    }

    private List<Exercise> getExercisesByRoutineId(int routineId) {
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
                exercises.add(new Exercise(id, name, muscleGroup, reps, sets, weight)); // Asegúrate de que Exercise tenga el constructor adecuado
            } while (cursor.moveToNext());
            cursor.close();
        }
        return exercises;
    }

    // Método para actualizar una rutina
    public void updateRoutine(int routineId, String name, List<Exercise> exercises) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        db.update("routines", values, "id = ?", new String[]{String.valueOf(routineId)});

        // Actualizar los ejercicios asociados a la rutina
        db.delete("exercises", "routine_id = ?", new String[]{String.valueOf(routineId)});
        for (Exercise exercise : exercises) {
            insertExercise(routineId, exercise);
        }
    }

    // Método para obtener las rutinas por usuario
    public List<Routine> getRoutinesByUser(String username) {
        List<Routine> routines = new ArrayList<>();
        Cursor cursor = db.query("routines", null, "username = ?", new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                List<Exercise> exercises = getExercisesByRoutineId(id);
                routines.add(new Routine(id, name, exercises, username));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return routines;
    }
}
