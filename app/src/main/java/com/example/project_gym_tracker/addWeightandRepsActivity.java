package com.example.project_gym_tracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addWeightandRepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_weight_and_reps);

        Button restarPesoBtn = findViewById(R.id.restarPesobtn);
        Button sumarPesoBtn = findViewById(R.id.sumarPesobtn);
        Button restarRepBtn = findViewById(R.id.restarrepbtn);
        Button sumarRepBtn = findViewById(R.id.sumarrepbtn);
        EditText pesoEditText = findViewById(R.id.editTextNumberDecimal);
        EditText repeticionesEditText = findViewById(R.id.editTextNumberDecimal2);

        // Sumar y Restar Peso
        restarPesoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarValor(pesoEditText, -1);
            }
        });

        sumarPesoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarValor(pesoEditText, 1);
            }
        });

        // Sumar y Restar Repeticiones
        restarRepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarValor(repeticionesEditText, -1);
            }
        });

        sumarRepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarValor(repeticionesEditText, 1);
            }
        });
    }

    private void modificarValor(EditText editText, int cambio) {
        String valorStr = editText.getText().toString();
        int valor;
        if (valorStr.isEmpty()) {
            valor = 0;
        } else {
            valor = Integer.parseInt(valorStr);
        }
        valor += cambio;
        if (valor < 0) {
            valor = 0; // Asegurarse de que el valor no sea negativo
        }
        editText.setText(String.valueOf(valor));
    }



}
