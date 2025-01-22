package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class seatchosse extends AppCompatActivity {

    private static final String PREFS_NAME = "SeatSelectionPrefs";
    private static final String SEAT_PREFIX = "seat_";

    private ArrayList<String> selectedSeats = new ArrayList<>();
    private int seatPrice = 0;
    private String airline;
    private String seatClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatchosse);

        // Load seat selections from SharedPreferences
        loadSeatSelections();

        // Initialize seat buttons
        initializeSeats();

        // Retrieve values from the intent
        airline = getIntent().getStringExtra("airline");
        seatClass = getIntent().getStringExtra("seatClass");

        // Set seat price
        if (airline != null && seatClass != null) {
            seatPrice = getSeatPrice(airline, seatClass);
        }

        // Display seat information
        ((TextView) findViewById(R.id.priceTextView)).setText("Seat Price: $" + seatPrice);
        ((TextView) findViewById(R.id.airlineTextView)).setText("Airline: " + airline);
        ((TextView) findViewById(R.id.seatClassTextView)).setText("Class: " + seatClass);

        // Set button listeners
        findViewById(R.id.confirmButton).setOnClickListener(v -> onConfirmClicked());

    }

    private void initializeSeats() {
        String[] seatRows = {"A", "B", "D", "E", "F"};
        for (String row : seatRows) {
            for (int i = 1; i <= 5; i++) {
                String seatId = "seat" + row + i;
                int resId = getResources().getIdentifier(seatId, "id", getPackageName());
                TextView seat = findViewById(resId);

                if (seat != null) {
                    String seatName = row + i;
                    seat.setOnClickListener(v -> onSeatClicked(seat, seatName));

                    // Set background based on selection state
                    if (isSeatSelected(seatName)) {
                        seat.setBackgroundResource(R.drawable.seat_selected);
                    } else {
                        seat.setBackgroundResource(R.drawable.seat_default);
                    }
                }
            }
        }
    }

    private void onSeatClicked(TextView seat, String seatName) {
        if (isSeatSelected(seatName)) {
            deselectSeat(seat, seatName);
        } else {
            selectSeat(seat, seatName);
        }
    }

    private void selectSeat(TextView seat, String seatName) {
        selectedSeats.add(seatName);
        seat.setBackgroundResource(R.drawable.seat_selected);
        saveSeatSelections();
        Toast.makeText(this, "Seat " + seatName + " selected!", Toast.LENGTH_SHORT).show();
    }

    private void deselectSeat(TextView seat, String seatName) {
        selectedSeats.remove(seatName);
        seat.setBackgroundResource(R.drawable.seat_default);
        saveSeatSelections();
        Toast.makeText(this, "Seat " + seatName + " deselected!", Toast.LENGTH_SHORT).show();
    }

    private void resetSeats() {
        selectedSeats.clear();

        String[] seatRows = {"A", "B", "D", "E", "F"};
        for (String row : seatRows) {
            for (int i = 1; i <= 5; i++) {
                String seatId = "seat" + row + i;
                int resId = getResources().getIdentifier(seatId, "id", getPackageName());
                TextView seat = findViewById(resId);

                if (seat != null) {
                    seat.setBackgroundResource(R.drawable.seat_default);
                }
            }
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        Toast.makeText(this, "All seats have been reset.", Toast.LENGTH_SHORT).show();
    }

    private boolean isSeatSelected(String seatName) {
        return selectedSeats.contains(seatName);
    }

    private void saveSeatSelections() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        for (String seat : selectedSeats) {
            editor.putBoolean(SEAT_PREFIX + seat, true);
        }
        editor.apply();
    }

    private void loadSeatSelections() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        selectedSeats.clear();

        String[] seatRows = {"A", "B", "D", "E", "F"};
        for (String row : seatRows) {
            for (int i = 1; i <= 5; i++) {
                String seatName = row + i;
                if (sharedPreferences.getBoolean(SEAT_PREFIX + seatName, false)) {
                    selectedSeats.add(seatName);
                }
            }
        }
    }

    private void onConfirmClicked() {
        if (selectedSeats.isEmpty()) {
            Toast.makeText(this, "Please select at least one seat.", Toast.LENGTH_SHORT).show();
            return;
        }

        int totalPrice = seatPrice * selectedSeats.size();
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("selectedSeats", selectedSeats);
        intent.putExtra("seatPrice", seatPrice);
        intent.putExtra("airline", airline);
        intent.putExtra("seatClass", seatClass);
        intent.putExtra("totalPrice", totalPrice);

        startActivity(intent);
    }

    private int getSeatPrice(String airline, String seatClass) {
        switch (airline) {
            case "Southwest":
                return seatClass.equals("Economy") ? 200 : seatClass.equals("Business") ? 350 : 500;
            case "American Airlines":
                return seatClass.equals("Economy") ? 180 : seatClass.equals("Business") ? 300 : 450;
            case "Delta":
                return seatClass.equals("Economy") ? 220 : seatClass.equals("Business") ? 375 : 525;
            case "United":
                return seatClass.equals("Economy") ? 210 : seatClass.equals("Business") ? 325 : 475;
            default:
                return 0;
        }
    }
}
