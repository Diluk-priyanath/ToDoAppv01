package com.example.to_do_app_v01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView createOne;
    EditText enter_email;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createOne = findViewById(R.id.signup);
        enter_email = findViewById(R.id.textInputEmail);
        password = findViewById(R.id.textInputPassword);
        login = findViewById(R.id.btn_lg);

        createOne.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        login.setOnClickListener(v -> {
            String email = enter_email.getText().toString();
            String pass = password.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // To be implemented
        });



    }
}