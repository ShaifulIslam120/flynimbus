package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Airlineschose extends AppCompatActivity {

    private TextView seatClassSouthwest, priceSouthwest;
    private TextView seatClassAmerican, priceAmerican;
    private TextView seatClassDelta, priceDelta;
    private TextView seatClassUnited, priceUnited;

    private TextView textViewFromSouthwest, textViewToSouthwest;
    private TextView textViewFromAmerican, textViewToAmerican;
    private TextView textViewFromDelta, textViewToDelta;
    private TextView textViewFromUnited, textViewToUnited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airlineschose);

        // Initialize TextViews for Southwest Airlines
        seatClassSouthwest = findViewById(R.id.seatClassSouthwest);
        priceSouthwest = findViewById(R.id.priceSouthwest);
        textViewFromSouthwest = findViewById(R.id.textViewFromSouthwest);
        textViewToSouthwest = findViewById(R.id.textViewToSouthwest);

        // Initialize TextViews for American Airlines
        seatClassAmerican = findViewById(R.id.seatClassAmerican);
        priceAmerican = findViewById(R.id.priceAmerican);
        textViewFromAmerican = findViewById(R.id.textViewFromAmerican);
        textViewToAmerican = findViewById(R.id.textViewToAmerican);

        // Initialize TextViews for Delta Airlines
        seatClassDelta = findViewById(R.id.seatClassDelta);
        priceDelta = findViewById(R.id.priceDelta);
        textViewFromDelta = findViewById(R.id.textViewFromDelta);
        textViewToDelta = findViewById(R.id.textViewToDelta);

        // Initialize TextViews for United Airlines
        seatClassUnited = findViewById(R.id.seatClassUnited);
        priceUnited = findViewById(R.id.priceUnited);
        textViewFromUnited = findViewById(R.id.textViewFromUnited);
        textViewToUnited = findViewById(R.id.textViewToUnited);

        // Get the seat class from the Intent
        String selectedSeatClass = getIntent().getStringExtra("selectedSeatClass");

        // Get the 'From' and 'To' values
        String fromValue = getIntent().getStringExtra("from_value");
        String toValue = getIntent().getStringExtra("to_value");

        // Set the seat class, price, and 'From' and 'To' for all airlines
        setAirlineData(selectedSeatClass, fromValue, toValue);

        // Add click listener to Southwest Airlines image
        ImageView southwestImage = findViewById(R.id.imageViewSouthwest);
        southwestImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the seat prices for all airlines
                int southwestPrice = getSouthwestPrice(selectedSeatClass);
                int americanPrice = getAmericanPrice(selectedSeatClass);
                int deltaPrice = getDeltaPrice(selectedSeatClass);
                int unitedPrice = getUnitedPrice(selectedSeatClass);

                // Calculate total cost (example: 3 seats selected)
                int selectedSeatCount = 3;
                double totalCost = selectedSeatCount * southwestPrice; // Use the selected airline's price here

                // Pass all seat prices to seatchosse activity
                Intent intent = new Intent(Airlineschose.this, seatchosse.class);
                intent.putExtra("southwestPrice", southwestPrice);
                intent.putExtra("americanPrice", americanPrice);
                intent.putExtra("deltaPrice", deltaPrice);
                intent.putExtra("unitedPrice", unitedPrice);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
            }
        });


        // Add click listener to American Airlines image
        ImageView americanImage = findViewById(R.id.imageViewAmerican);
        americanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the seat prices for all airlines
                int southwestPrice = getSouthwestPrice(selectedSeatClass);
                int americanPrice = getAmericanPrice(selectedSeatClass);
                int deltaPrice = getDeltaPrice(selectedSeatClass);
                int unitedPrice = getUnitedPrice(selectedSeatClass);

                // Calculate total cost (example: 3 seats selected)
                int selectedSeatCount = 3;
                double totalCost = selectedSeatCount * americanPrice; // Use the selected airline's price here

                // Pass all seat prices to seatchosse activity
                Intent intent = new Intent(Airlineschose.this, seatchosse.class);
                intent.putExtra("southwestPrice", southwestPrice);
                intent.putExtra("americanPrice", americanPrice);
                intent.putExtra("deltaPrice", deltaPrice);
                intent.putExtra("unitedPrice", unitedPrice);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
            }
        });

        // Add click listener to Delta Airlines image
        ImageView deltaImage = findViewById(R.id.imageViewDelta);
        deltaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the seat prices for all airlines
                int southwestPrice = getSouthwestPrice(selectedSeatClass);
                int americanPrice = getAmericanPrice(selectedSeatClass);
                int deltaPrice = getDeltaPrice(selectedSeatClass);
                int unitedPrice = getUnitedPrice(selectedSeatClass);

                // Calculate total cost (example: 3 seats selected)
                int selectedSeatCount = 3;
                double totalCost = selectedSeatCount * deltaPrice; // Use the selected airline's price here

                // Pass all seat prices to seatchosse activity
                Intent intent = new Intent(Airlineschose.this, seatchosse.class);
                intent.putExtra("southwestPrice", southwestPrice);
                intent.putExtra("americanPrice", americanPrice);
                intent.putExtra("deltaPrice", deltaPrice);
                intent.putExtra("unitedPrice", unitedPrice);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
            }
        });

        // Add click listener to United Airlines image
        ImageView unitedImage = findViewById(R.id.imageViewUnited);
        unitedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the seat prices for all airlines
                int southwestPrice = getSouthwestPrice(selectedSeatClass);
                int americanPrice = getAmericanPrice(selectedSeatClass);
                int deltaPrice = getDeltaPrice(selectedSeatClass);
                int unitedPrice = getUnitedPrice(selectedSeatClass);

                // Calculate total cost (example: 3 seats selected)
                int selectedSeatCount = 3;
                double totalCost = selectedSeatCount * unitedPrice; // Use the selected airline's price here

                // Pass all seat prices to seatchosse activity
                Intent intent = new Intent(Airlineschose.this, seatchosse.class);
                intent.putExtra("southwestPrice", southwestPrice);
                intent.putExtra("americanPrice", americanPrice);
                intent.putExtra("deltaPrice", deltaPrice);
                intent.putExtra("unitedPrice", unitedPrice);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
            }
        });
    }

    // Method to set the seat class, price, and 'From' and 'To' for all airlines
    private void setAirlineData(String seatClass, String from, String to) {
        // Set the seat class for all airlines
        String seatClassText = "Seat Class: " + seatClass;

        // Southwest Airlines
        seatClassSouthwest.setText(seatClassText);
        priceSouthwest.setText("Price: $" + getSouthwestPrice(seatClass));
        textViewFromSouthwest.setText(from);
        textViewToSouthwest.setText(to);

        // American Airlines
        seatClassAmerican.setText(seatClassText);
        priceAmerican.setText("Price: $" + getAmericanPrice(seatClass));
        textViewFromAmerican.setText(from);
        textViewToAmerican.setText(to);

        // Delta Airlines
        seatClassDelta.setText(seatClassText);
        priceDelta.setText("Price: $" + getDeltaPrice(seatClass));
        textViewFromDelta.setText(from);
        textViewToDelta.setText(to);

        // United Airlines
        seatClassUnited.setText(seatClassText);
        priceUnited.setText("Price: $" + getUnitedPrice(seatClass));
        textViewFromUnited.setText(from);
        textViewToUnited.setText(to);
    }

    // Methods to get the price based on the seat class for Southwest Airlines
    private int getSouthwestPrice(String seatClass) {
        switch (seatClass) {
            case "Economy":
                return 150;
            case "Business":
                return 250;
            case "First Class":
                return 350;
            default:
                return 150; // Default price
        }
    }

    // Methods to get the price based on the seat class for American Airlines
    private int getAmericanPrice(String seatClass) {
        switch (seatClass) {
            case "Economy":
                return 180;
            case "Business":
                return 280;
            case "First Class":
                return 380;
            default:
                return 180; // Default price
        }
    }

    // Methods to get the price based on the seat class for Delta Airlines
    private int getDeltaPrice(String seatClass) {
        switch (seatClass) {
            case "Economy":
                return 160;
            case "Business":
                return 260;
            case "First Class":
                return 360;
            default:
                return 160; // Default price
        }
    }

    // Methods to get the price based on the seat class for United Airlines
    private int getUnitedPrice(String seatClass) {
        switch (seatClass) {
            case "Economy":
                return 170;
            case "Business":
                return 270;
            case "First Class":
                return 370;
            default:
                return 170; // Default price
        }
    }
}
