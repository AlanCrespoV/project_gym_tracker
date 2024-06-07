package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EditRoutineActivity extends AppCompatActivity {

    private EditText routineNameEditText;
    private Spinner daySpinner;
    private Spinner bodyPartSpinner;
    private EditText exerciseNameEditText;
    private EditText exerciseSetsEditText;
    private Button addExerciseButton;
    private Button saveRoutineButton;
    private Button startRoutineButton;
    private RecyclerView exercisesRecyclerView;
    private ExercisesAdapter exercisesAdapter;
    private RoutinesRepository routinesRepository;
    private int routineId;
    private Routine routine;
    private List<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);

        routineNameEditText = findViewById(R.id.routineName);
        daySpinner = findViewById(R.id.daySpinner);
        bodyPartSpinner = findViewById(R.id.bodyPartSpinner);
        exerciseNameEditText = findViewById(R.id.exerciseName);
        exerciseSetsEditText = findViewById(R.id.exerciseSets);
        addExerciseButton = findViewById(R.id.addExerciseButton);
        saveRoutineButton = findViewById(R.id.saveRoutineButton);
        startRoutineButton = findViewById(R.id.startRoutineButton);
        exercisesRecyclerView = findViewById(R.id.exercisesRecyclerView);

        routinesRepository = new RoutinesRepository(this);
        exercises = new ArrayList<>();
        exercisesAdapter = new ExercisesAdapter(exercises);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        exercisesRecyclerView.setAdapter(exercisesAdapter);

        routineId = getIntent().getIntExtra("ROUTINE_ID", -1);
        if (routineId == -1) {
            finish();
            return;
        }

        routine = routinesRepository.getRoutineById(routineId);
        if (routine != null) {
            routineNameEditText.setText(routine.getName());
            // Set daySpinner and bodyPartSpinner selections based on the routine details
            exercises.addAll(routine.getExercises());
            exercisesAdapter.notifyDataSetChanged();
        }

        // Configurar Spinners
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        ArrayAdapter<CharSequence> bodyPartAdapter = ArrayAdapter.createFromResource(this,
                R.array.body_parts_array, android.R.layout.simple_spinner_item);
        bodyPartAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodyPartSpinner.setAdapter(bodyPartAdapter);

        addExerciseButton.setOnClickListener(view -> {
            String exerciseName = exerciseNameEditText.getText().toString();
            String muscleGroup = bodyPartSpinner.getSelectedItem().toString();
            String setsStr = exerciseSetsEditText.getText().toString();

            if (exerciseName.isEmpty() || muscleGroup.isEmpty() || setsStr.isEmpty()) {
                Toast.makeText(EditRoutineActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                int sets = Integer.parseInt(setsStr);
                Exercise exercise = new Exercise(exerciseName, muscleGroup, 0, sets, 0.0);
                exercises.add(exercise);
                exercisesAdapter.notifyItemInserted(exercises.size() - 1);

                exerciseNameEditText.setText("");
                exerciseSetsEditText.setText("");
                Toast.makeText(EditRoutineActivity.this, "Ejercicio agregado correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        saveRoutineButton.setOnClickListener(view -> {
            String routineName = routineNameEditText.getText().toString();

            if (routineName.isEmpty()) {
                Toast.makeText(EditRoutineActivity.this, "Por favor, ingrese el nombre de la rutina", Toast.LENGTH_SHORT).show();
            } else {
                routine.setName(routineName);
                routine.setExercises(exercises);
                routinesRepository.updateRoutine(routineId, routineName, exercises); // Llamar al método con los argumentos correctos

                Toast.makeText(EditRoutineActivity.this, "Rutina actualizada correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditRoutineActivity.this, WelcomeActivity.class);
                intent.putExtra("USERNAME", routine.getUsername());
                startActivity(intent);
                finish();
            }
        });

        startRoutineButton.setOnClickListener(view -> {
            // Implementar la lógica para empezar la rutina
            Toast.makeText(EditRoutineActivity.this, "Rutina comenzada", Toast.LENGTH_SHORT).show();
        });
    }
}
