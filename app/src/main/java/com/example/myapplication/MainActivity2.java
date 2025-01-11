package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the View Binding
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set OnClickListener for the "Get Started" button
        binding.button.setOnClickListener(v -> {
            // Navigate to RegisterActivity
            Intent intent = new Intent(MainActivity2.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for the "I have an account" button
        binding.button2.setOnClickListener(v -> {
            // Navigate to LoginActivity
            Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
