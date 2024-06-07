package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWeightAndRepsActivity extends AppCompatActivity {

    private EditText repsEditText;
    private EditText setsEditText;
    private EditText weightEditText;
    private Button saveWeightAndRepsButton;
    private RoutinesRepository routinesRepository;
    private int exerciseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_weight_and_reps);

        repsEditText = findViewById(R.id.editTextNumberDecimal2);
        setsEditText = findViewById(R.id.editTextNumberDecimal);
        weightEditText = findViewById(R.id.editTextNumberDecimal);
        saveWeightAndRepsButton = findViewById(R.id.addwandrepsbtn);

        routinesRepository = new RoutinesRepository(this);

        exerciseId = getIntent().getIntExtra("EXERCISE_ID", -1);

        saveWeightAndRepsButton.setOnClickListener(view -> {
            String repsStr = repsEditText.getText().toString();
            String setsStr = setsEditText.getText().toString();
            String weightStr = weightEditText.getText().toString();

            if (repsStr.isEmpty() || setsStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(AddWeightAndRepsActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                int reps = Integer.parseInt(repsStr);
                int sets = Integer.parseInt(setsStr);
                double weight = Double.parseDouble(weightStr);

                routinesRepository.updateExercise(exerciseId, reps, sets, weight);

                Toast.makeText(AddWeightAndRepsActivity.this, "Peso y repeticiones actualizados correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddWeightAndRepsActivity.this, ViewRoutines.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
