package com.example.project_gym_tracker;

public class Exercise {
    private int id;
    private String name;
    private String muscleFocus;
    private int repetitions;
    private int sets;
    private double weight;

    // Constructor con ID
    public Exercise(int id, String name, String muscleFocus, int repetitions, int sets, double weight) {
        this.id = id;
        this.name = name;
        this.muscleFocus = muscleFocus;
        this.repetitions = repetitions;
        this.sets = sets;
        this.weight = weight;
    }

    // Constructor sin ID
    public Exercise(String name, String muscleFocus, int repetitions, int sets, double weight) {
        this.name = name;
        this.muscleFocus = muscleFocus;
        this.repetitions = repetitions;
        this.sets = sets;
        this.weight = weight;
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

    public String getMuscleFocus() {
        return muscleFocus;
    }

    public void setMuscleFocus(String muscleFocus) {
        this.muscleFocus = muscleFocus;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
