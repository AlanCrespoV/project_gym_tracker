package com.example.project_gym_tracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.email);
        resetPasswordButton = findViewById(R.id.reset_password_button);
        userRepository = new UserRepository(this);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Por favor, introduce tu correo electrónico", Toast.LENGTH_SHORT).show();
                } else {
                    boolean userExists = userRepository.isValidUser(email);
                    if (userExists) {
                        // Lógica para restablecer la contraseña
                        Toast.makeText(ForgotPasswordActivity.this, "Se ha enviado un enlace de restablecimiento de contraseña a tu correo electrónico", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Correo electrónico no encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
