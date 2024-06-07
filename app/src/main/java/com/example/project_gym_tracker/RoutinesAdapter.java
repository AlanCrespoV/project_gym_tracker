package com.example.project_gym_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.RoutineViewHolder> {

    private List<Routine> routines;
    private OnRoutineClickListener onRoutineClickListener;

    public interface OnRoutineClickListener {
        void onRoutineClick(int routineId);
    }

    public RoutinesAdapter(List<Routine> routines, OnRoutineClickListener onRoutineClickListener) {
        this.routines = routines;
        this.onRoutineClickListener = onRoutineClickListener;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine, parent, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        Routine routine = routines.get(position);
        holder.nameTextView.setText(routine.getName());
        StringBuilder exercisesText = new StringBuilder();
        for (Exercise exercise : routine.getExercises()) {
            exercisesText.append(exercise.getName()).append(", ");
        }
        holder.exercisesTextView.setText(exercisesText.toString());
        holder.itemView.setOnClickListener(v -> onRoutineClickListener.onRoutineClick(routine.getId()));
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public static class RoutineViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView exercisesTextView;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            exercisesTextView = itemView.findViewById(R.id.exercisesTextView);
        }
    }
}
