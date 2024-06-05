package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class ViewRoutines extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_routines);


        Button addWandRtestbtn = findViewById(R.id.addWandRtestbtn);
        addWandRtestbtn.setOnClickListener(view -> {
            Intent intent = new Intent(ViewRoutines.this, addWeightandRepsActivity.class);
            startActivity(intent);

        });

        Button addroutinebtn = findViewById(R.id.addroutinebtn);
        addroutinebtn.setOnClickListener(view -> {
            Intent intent = new Intent(ViewRoutines.this, AddExerciteActivity.class);
            startActivity(intent);

        });

    }



}
