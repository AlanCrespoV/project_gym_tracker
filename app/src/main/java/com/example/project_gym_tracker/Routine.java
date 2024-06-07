package com.example.project_gym_tracker;
import java.util.List;

public class Routine {
    private int id; // Añadir el ID
    private String name;
    private List<Exercise> exercises;
    private String username; // Añadir el nombre de usuario

    // Constructor
    public Routine(int id, String name, List<Exercise> exercises, String username) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
        this.username = username;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
