package com.example.to_do_app_v01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay for 3 seconds and then start the RegisterActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); // Use "getSharedPreferences" directly
                boolean LoggedIn = sharedPreferences.getBoolean("LoggedIn", false); // Default value should be false if key not found

                // Start the LoggedIn activity based on login status
                Intent intent;
                if (LoggedIn) {
                    // User is logged in, navigate to DashboardActivity
                    intent = new Intent(MainActivity.this, DashboardActivity.class);
                } else {
                    // User is not logged in, navigate to RegisterActivity
                    intent = new Intent(MainActivity.this, RegisterActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}
