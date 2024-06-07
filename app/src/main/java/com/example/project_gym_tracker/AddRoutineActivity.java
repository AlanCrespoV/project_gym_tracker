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

public class AddRoutineActivity extends AppCompatActivity {

    private EditText routineNameEditText;
    private Spinner daySpinner;
    private Spinner bodyPartSpinner;
    private EditText exerciseNameEditText;
    private EditText exerciseSetsEditText;
    private Button addExerciseButton;
    private Button saveRoutineButton;
    private RecyclerView exercisesRecyclerView;
    private ExercisesAdapter exercisesAdapter;
    private RoutinesRepository routinesRepository;
    private String currentUser;
    private List<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        routineNameEditText = findViewById(R.id.routineName);
        daySpinner = findViewById(R.id.daySpinner);
        bodyPartSpinner = findViewById(R.id.bodyPartSpinner);
        exerciseNameEditText = findViewById(R.id.exerciseName);
        exerciseSetsEditText = findViewById(R.id.exerciseSets);
        addExerciseButton = findViewById(R.id.addExerciseButton);
        saveRoutineButton = findViewById(R.id.saveRoutineButton);
        exercisesRecyclerView = findViewById(R.id.exercisesRecyclerView);

        routinesRepository = new RoutinesRepository(this);
        exercises = new ArrayList<>();
        exercisesAdapter = new ExercisesAdapter(exercises);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        exercisesRecyclerView.setAdapter(exercisesAdapter);

        currentUser = getIntent().getStringExtra("USERNAME");

        // Configurar los Spinners
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
                Toast.makeText(AddRoutineActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                int sets = Integer.parseInt(setsStr);
                Exercise exercise = new Exercise(exerciseName, muscleGroup, 0, sets, 0.0);
                exercises.add(exercise);
                exercisesAdapter.notifyItemInserted(exercises.size() - 1);

                exerciseNameEditText.setText("");
                exerciseSetsEditText.setText("");
                Toast.makeText(AddRoutineActivity.this, "Ejercicio agregado correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        saveRoutineButton.setOnClickListener(view -> {
            String routineName = routineNameEditText.getText().toString();
            String routineDay = daySpinner.getSelectedItem().toString();
            String bodyPart = bodyPartSpinner.getSelectedItem().toString();

            if (routineName.isEmpty()) {
                Toast.makeText(AddRoutineActivity.this, "Por favor, ingrese el nombre de la rutina", Toast.LENGTH_SHORT).show();
            } else {
                Routine routine = new Routine(routineName, exercises);
                routinesRepository.insertRoutine(routine, currentUser);

                Toast.makeText(AddRoutineActivity.this, "Rutina agregada correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddRoutineActivity.this, WelcomeActivity.class);
                intent.putExtra("USERNAME", currentUser);
                startActivity(intent);
                finish();
            }
        });
    }
}
