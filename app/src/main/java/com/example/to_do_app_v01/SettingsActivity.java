package com.example.to_do_app_v01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.to_do_app_v01.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    String email;
    String registerUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        email = getIntent().getStringExtra("Email");
        registerUsername = getIntent().getStringExtra("Username");

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("Email", email);
        registerUsername = sharedPreferences.getString("Username", registerUsername);

        binding.editUsername.setText(registerUsername);
        binding.editEmailname.setText(email);

        binding.btnLgOut.setOnClickListener(v -> showLogoutDialog());

        binding.btnEditinfo.setOnClickListener(v -> showEditInfoDialog());
    }

}
