package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RoutinesAdapter adapter;
    private RoutinesRepository routinesRepository;
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        routinesRepository = new RoutinesRepository(this);

        // Obtener el usuario actual desde el intent
        currentUser = getIntent().getStringExtra("USERNAME");
        if (currentUser == null) {
            // Si no hay un usuario actual, redirigir al LoginActivity
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Obtener rutinas del usuario actual desde la base de datos
        List<Routine> routines = routinesRepository.getRoutinesByUser(currentUser);

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.routinesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoutinesAdapter(routines);
        recyclerView.setAdapter(adapter);

        Button addRoutineButton = findViewById(R.id.addRoutineButton);
        addRoutineButton.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeActivity.this, AddRoutineActivity.class);
            intent.putExtra("USERNAME", currentUser);
            startActivity(intent);
        });
    }
}
