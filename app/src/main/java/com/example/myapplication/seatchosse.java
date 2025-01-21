package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class seatchosse extends AppCompatActivity {

    // Track seat selection status for all seats
    private boolean isA1Selected, isA2Selected, isA3Selected, isA4Selected, isA5Selected;
    private boolean isB1Selected, isB2Selected, isB3Selected, isB4Selected, isB5Selected;
    private boolean isD1Selected, isD2Selected, isD3Selected, isD4Selected, isD5Selected;
    private boolean isE1Selected, isE2Selected, isE3Selected, isE4Selected, isE5Selected;
    private boolean isF1Selected, isF2Selected, isF3Selected, isF4Selected, isF5Selected;

    private static final String PREFS_NAME = "SeatSelectionPrefs";
    private static final String SEAT_PREFIX = "seat_";

    private ArrayList<String> selectedSeats = new ArrayList<>();  // List to store selected seats

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatchosse);

        // Load seat selection from SharedPreferences
        loadSeatSelections();

        // Initialize all seats
        initializeSeats();

        // Find and set the Confirm button
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> onConfirmClicked());
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

                    // Set the background color based on whether the seat is selected
                    if (isSeatSelected(seatName)) {
                        seat.setBackgroundResource(R.drawable.seat_selected);  // Mark the seat as selected
                    }
                }
            }
        }
    }

    private void onSeatClicked(TextView seat, String seatName) {
        if (isSeatSelected(seatName)) {
            Toast.makeText(this, "Seat " + seatName + " is already selected!", Toast.LENGTH_SHORT).show();
        } else {
            selectSeat(seat, seatName);
        }
    }

    private boolean isSeatSelected(String seatName) {
        switch (seatName) {
            case "A1": return isA1Selected;
            case "A2": return isA2Selected;
            case "A3": return isA3Selected;
            case "A4": return isA4Selected;
            case "A5": return isA5Selected;
            case "B1": return isB1Selected;
            case "B2": return isB2Selected;
            case "B3": return isB3Selected;
            case "B4": return isB4Selected;
            case "B5": return isB5Selected;
            case "D1": return isD1Selected;
            case "D2": return isD2Selected;
            case "D3": return isD3Selected;
            case "D4": return isD4Selected;
            case "D5": return isD5Selected;
            case "E1": return isE1Selected;
            case "E2": return isE2Selected;
            case "E3": return isE3Selected;
            case "E4": return isE4Selected;
            case "E5": return isE5Selected;
            case "F1": return isF1Selected;
            case "F2": return isF2Selected;
            case "F3": return isF3Selected;
            case "F4": return isF4Selected;
            case "F5": return isF5Selected;
            default: return false;
        }
    }

    private void selectSeat(TextView seat, String seatName) {
        // Mark the seat as selected
        switch (seatName) {
            case "A1": isA1Selected = true; break;
            case "A2": isA2Selected = true; break;
            case "A3": isA3Selected = true; break;
            case "A4": isA4Selected = true; break;
            case "A5": isA5Selected = true; break;
            case "B1": isB1Selected = true; break;
            case "B2": isB2Selected = true; break;
            case "B3": isB3Selected = true; break;
            case "B4": isB4Selected = true; break;
            case "B5": isB5Selected = true; break;
            case "D1": isD1Selected = true; break;
            case "D2": isD2Selected = true; break;
            case "D3": isD3Selected = true; break;
            case "D4": isD4Selected = true; break;
            case "D5": isD5Selected = true; break;
            case "E1": isE1Selected = true; break;
            case "E2": isE2Selected = true; break;
            case "E3": isE3Selected = true; break;
            case "E4": isE4Selected = true; break;
            case "E5": isE5Selected = true; break;
            case "F1": isF1Selected = true; break;
            case "F2": isF2Selected = true; break;
            case "F3": isF3Selected = true; break;
            case "F4": isF4Selected = true; break;
            case "F5": isF5Selected = true; break;
        }

        seat.setBackgroundResource(R.drawable.seat_selected);  // Change seat background to selected
        Toast.makeText(this, "Seat " + seatName + " selected!", Toast.LENGTH_SHORT).show();

        // Add selected seat to the list
        if (!selectedSeats.contains(seatName)) {
            selectedSeats.add(seatName);  // Avoid adding duplicates
        }

        // Save the updated seat selection to SharedPreferences
        saveSeatSelections();
    }

    private void saveSeatSelections() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the selection state of each seat
        for (int row = 0; row < 5; row++) {
            for (int col = 1; col <= 5; col++) {
                String seatName = (char) ('A' + row) + String.valueOf(col);
                editor.putBoolean(SEAT_PREFIX + seatName, isSeatSelected(seatName));
            }
        }

        editor.apply();  // Apply changes asynchronously
    }

    private void loadSeatSelections() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load the seat selection states from SharedPreferences
        for (int row = 0; row < 5; row++) {
            for (int col = 1; col <= 5; col++) {
                String seatName = (char) ('A' + row) + String.valueOf(col);
                boolean selected = sharedPreferences.getBoolean(SEAT_PREFIX + seatName, false);
                setSeatSelection(seatName, selected);
            }
        }
    }

    private void setSeatSelection(String seatName, boolean isSelected) {
        switch (seatName) {
            case "A1": isA1Selected = isSelected; break;
            case "A2": isA2Selected = isSelected; break;
            case "A3": isA3Selected = isSelected; break;
            case "A4": isA4Selected = isSelected; break;
            case "A5": isA5Selected = isSelected; break;
            case "B1": isB1Selected = isSelected; break;
            case "B2": isB2Selected = isSelected; break;
            case "B3": isB3Selected = isSelected; break;
            case "B4": isB4Selected = isSelected; break;
            case "B5": isB5Selected = isSelected; break;
            case "D1": isD1Selected = isSelected; break;
            case "D2": isD2Selected = isSelected; break;
            case "D3": isD3Selected = isSelected; break;
            case "D4": isD4Selected = isSelected; break;
            case "D5": isD5Selected = isSelected; break;
            case "E1": isE1Selected = isSelected; break;
            case "E2": isE2Selected = isSelected; break;
            case "E3": isE3Selected = isSelected; break;
            case "E4": isE4Selected = isSelected; break;
            case "E5": isE5Selected = isSelected; break;
            case "F1": isF1Selected = isSelected; break;
            case "F2": isF2Selected = isSelected; break;
            case "F3": isF3Selected = isSelected; break;
            case "F4": isF4Selected = isSelected; break;
            case "F5": isF5Selected = isSelected; break;
        }
    }

    private void onConfirmClicked() {
        // Check if at least one seat is selected
        if (selectedSeats.isEmpty()) {
            Toast.makeText(this, "No seat selected! Please select at least one seat.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Start TicketActivity and pass the selected seats list
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putStringArrayListExtra("selectedSeats", selectedSeats);
        startActivity(intent);
    }
}
