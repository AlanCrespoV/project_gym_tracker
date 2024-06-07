package com.example.project_gym_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises;

    public ExercisesAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.exerciseNameTextView.setText(exercise.getName());
        holder.muscleGroupTextView.setText(exercise.getMuscleFocus());
        holder.setsTextView.setText(String.valueOf(exercise.getSets()));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameTextView;
        TextView muscleGroupTextView;
        TextView setsTextView;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.exercise_name);
            muscleGroupTextView = itemView.findViewById(R.id.muscle_group);
            setsTextView = itemView.findViewById(R.id.sets);
        }
    }
}
