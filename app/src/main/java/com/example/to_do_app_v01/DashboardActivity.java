package com.example.to_do_app_v01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.to_do_app_v01.databinding.ActivityDashboardBinding;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    listAdapter listAdapter;
    ArrayList<todoData> dataArrayList = new ArrayList<>();
    sharedPreferences sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String registerUsername = sharedPreferences.getString("Username", "");
        String email = sharedPreferences.getString("Email", "");

        binding.textViewName.setText(registerUsername);

        sharedPrefManager = new sharedPreferences(this);

        // Load saved to-do items
        ArrayList<String> savedItems = sharedPrefManager.getTodoList();
        for (String item : savedItems) {
            todoData todoData = new todoData(item, R.drawable.edit, R.drawable.delete);
            dataArrayList.add(todoData);
        }

        listAdapter = new listAdapter(DashboardActivity.this, dataArrayList);
        binding.todoitemlistview.setAdapter(listAdapter);
        binding.todoitemlistview.setClickable(false);


        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });


        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showAddItemDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);

        // Inflate the custom layout/view
        final View customLayout = getLayoutInflater().inflate(R.layout.add_item_dialog, null);
        builder.setView(customLayout);

        // Set the title
        builder.setTitle("Add an item");

        // Add action buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            // Get the input from EditText
            EditText editTextItem = customLayout.findViewById(R.id.edit_text_item);
            String item = editTextItem.getText().toString();

            // Handle the "Add" button click
            if (!item.isEmpty()) {
                ArrayList<String> currentList = sharedPrefManager.getTodoList();
                currentList.add(item);
                sharedPrefManager.saveTodoList(currentList);

                // Update the ListView
                todoData newTodoData = new todoData(item, R.drawable.edit, R.drawable.delete);
                dataArrayList.add(newTodoData);
                listAdapter.notifyDataSetChanged();

                Toast.makeText(this, "Item added: " + item, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Item cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.white));
    }




}