package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewRoutines extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_routines); // Asegúrate de usar el layout correcto

        Button addWandRtestbtn = findViewById(R.id.addWandRtestbtn);
        addWandRtestbtn.setOnClickListener(view -> {
            Intent intent = new Intent(ViewRoutines.this, AddWeightAndRepsActivity.class);
            startActivity(intent);
        });

        Button addroutinebtn = findViewById(R.id.addroutinebtn);
        addroutinebtn.setOnClickListener(view -> {
            Intent intent = new Intent(ViewRoutines.this, AddExerciteActivity.class);
            startActivity(intent);
        });

        Button addRoutineButton = findViewById(R.id.addRoutineButton);
        addRoutineButton.setOnClickListener(view -> {
            Intent intent = new Intent(ViewRoutines.this, AddRoutineActivity.class);
            startActivity(intent);
        });
    }
}
