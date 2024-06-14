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

            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String registeredUser = sharedPreferences.getString("Email", "");
            String registeredPass = sharedPreferences.getString("Password", "");
            String registerUsername = sharedPreferences.getString("Username", "");

            if (email.equals(registeredUser) && pass.equals(registeredPass)) {
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LoggedIn", true);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

        });



    }
}