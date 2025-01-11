package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Airlineschose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airlineschose);

        // Find ImageView elements for each airline
        ImageView imageViewWorld = findViewById(R.id.imageViewWorld);
        ImageView imageViewSouthwest = findViewById(R.id.imageViewSouthwest);
        ImageView imageViewAmerican = findViewById(R.id.imageViewAmerican);
        ImageView imageViewDelta = findViewById(R.id.imageViewDelta);

        // Set onClickListener for World Icon
        imageViewWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Airlineschose.this, "World icon clicked!", Toast.LENGTH_SHORT).show();
                // Add additional logic for the world icon click here
            }
        });

        // Set onClickListener for Southwest Airlines Image
        imageViewSouthwest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Airlineschose.this, "Southwest Airlines clicked!", Toast.LENGTH_SHORT).show();
                // Add logic for Southwest Airlines click here
            }
        });

        // Set onClickListener for American Airlines Image
        imageViewAmerican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Airlineschose.this, "American Airlines clicked!", Toast.LENGTH_SHORT).show();
                // Add logic for American Airlines click here
            }
        });

        // Set onClickListener for Delta Airlines Image
        imageViewDelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Airlineschose.this, "Delta Airlines clicked!", Toast.LENGTH_SHORT).show();
                // Add logic for Delta Airlines click here
            }
        });
    }
}
