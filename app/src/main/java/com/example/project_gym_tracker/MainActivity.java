package com.example.project_gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

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

        // Inicializar la instancia de SQLite
        db = DatabaseManager.getInstance(this).getDatabase();

        if (usuarioHaIniciadoSesion()) {
            // Si el usuario ha iniciado sesión, iniciamos WelcomeActivity
            iniciarWelcomeActivity();
        }
    }

    private boolean usuarioHaIniciadoSesion() {
        // Implementar la lógica de verificación de sesión con SQLite
        // Ejemplo: Comprobar si hay un usuario en la base de datos
        return true;
    }

    // Método para iniciar WelcomeActivity
    private void iniciarWelcomeActivity() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
