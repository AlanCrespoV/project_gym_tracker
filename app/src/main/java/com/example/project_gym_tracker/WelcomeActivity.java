package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView routinesRecyclerView;
    private RoutinesAdapter routinesAdapter;
    private RoutinesRepository routinesRepository;
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        routinesRecyclerView = findViewById(R.id.routinesRecyclerView);
        Button addRoutineButton = findViewById(R.id.addRoutineButton);

        routinesRepository = new RoutinesRepository(this);
        currentUser = getIntent().getStringExtra("USERNAME");

        // Obtener las rutinas del usuario actual
        List<Routine> routines = routinesRepository.getRoutinesByUser(currentUser);
        routinesAdapter = new RoutinesAdapter(routines, routineId -> {
            Intent intent = new Intent(WelcomeActivity.this, EditRoutineActivity.class);
            intent.putExtra("ROUTINE_ID", routineId);
            startActivity(intent);
        });

        routinesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        routinesRecyclerView.setAdapter(routinesAdapter);

        addRoutineButton.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeActivity.this, AddRoutineActivity.class);
            intent.putExtra("USERNAME", currentUser);
            startActivity(intent);
        });
    }
}
