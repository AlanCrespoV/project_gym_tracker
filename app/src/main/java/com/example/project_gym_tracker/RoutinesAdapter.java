package com.example.project_gym_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> {

    private List<Routine> routines;

    // Constructor
    public RoutinesAdapter(List<Routine> routines) {
        this.routines = routines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Routine routine = routines.get(position);
        holder.bind(routine);
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    // Clase ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView exercisesTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            exercisesTextView = itemView.findViewById(R.id.exercisesTextView);
        }

        public void bind(Routine routine) {
            nameTextView.setText(routine.getName());

            StringBuilder exercisesBuilder = new StringBuilder();
            for (Exercise exercise : routine.getExercises()) {
                exercisesBuilder.append(exercise.getName()).append("\n");
            }
            exercisesTextView.setText(exercisesBuilder.toString());
        }
    }
}

