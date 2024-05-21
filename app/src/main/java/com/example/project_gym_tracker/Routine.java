package com.example.project_gym_tracker;
import java.util.List;

public class Routine {
    private String name;
    private List<Exercise> exercises;

    // Constructor
    public Routine(String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    // Getters y setters
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
}
