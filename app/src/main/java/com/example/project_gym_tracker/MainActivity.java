package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addexercitetibdbtn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar la instancia de Firestore
        db = FirebaseFirestore.getInstance();
        if (usuarioHaIniciadoSesion()) {
            // Si el usuario ha iniciado sesión, iniciamos WelcomeActivity
            iniciarWelcomeActivity();
        }
    }
    private boolean usuarioHaIniciadoSesion() {
        return true;
    }

    // Método para iniciar WelcomeActivity
    private void iniciarWelcomeActivity() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
