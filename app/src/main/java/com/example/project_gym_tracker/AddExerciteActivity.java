package com.example.project_gym_tracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddExerciteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_exercite);

        Spinner categoryexercitespinner = findViewById(R.id.categoryexercitespinner);
        String[] datos = new String[] {"","Abdominales", "Espalda", "Biceps", "Pecho", "Pierna","Hombros","Triceps"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);

        categoryexercitespinner.setAdapter(adapter);


    }


}