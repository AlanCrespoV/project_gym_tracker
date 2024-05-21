package com.example.project_gym_tracker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RoutinesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Crear una lista de rutinas con ejercicios de ejemplo (puedes reemplazar esto con tus propios datos)
        List<Routine> routines = new ArrayList<>();
        List<Exercise> exercises1 = new ArrayList<>();
        exercises1.add(new Exercise("Ejercicio 1", "Piernas", 10, 3, 50.0));
        exercises1.add(new Exercise("Ejercicio 2", "Espalda", 12, 4, 60.0));
        Routine routine1 = new Routine("Rutina 1", exercises1);
        routines.add(routine1);

        List<Exercise> exercises2 = new ArrayList<>();
        exercises2.add(new Exercise("Ejercicio 3", "Pecho", 8, 3, 70.0));
        exercises2.add(new Exercise("Ejercicio 4", "Brazos", 15, 3, 40.0));
        Routine routine2 = new Routine("Rutina 2", exercises2);
        routines.add(routine2);

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.routinesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoutinesAdapter(routines);
        recyclerView.setAdapter(adapter);
    }
}
