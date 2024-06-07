package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddExerciteActivity extends AppCompatActivity {

    private EditText exerciseNameEditText;
    private Spinner muscleGroupSpinner;
    private Button saveExerciseButton;
    private RoutinesRepository routinesRepository;
    private int routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercite);

        exerciseNameEditText = findViewById(R.id.nameexerciteinput);
        muscleGroupSpinner = findViewById(R.id.categoryexercitespinner);
        saveExerciseButton = findViewById(R.id.addexercitebdbtn);

        routinesRepository = new RoutinesRepository(this);

        routineId = getIntent().getIntExtra("ROUTINE_ID", -1);

        saveExerciseButton.setOnClickListener(view -> {
            String exerciseName = exerciseNameEditText.getText().toString();
            String muscleGroup = muscleGroupSpinner.getSelectedItem().toString();

            if (exerciseName.isEmpty() || muscleGroup.isEmpty()) {
                Toast.makeText(AddExerciteActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Exercise exercise = new Exercise(exerciseName, muscleGroup, 0, 0, 0.0);
                routinesRepository.insertExercise(routineId, exercise);

                Toast.makeText(AddExerciteActivity.this, "Ejercicio agregado correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddExerciteActivity.this, ViewRoutines.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
