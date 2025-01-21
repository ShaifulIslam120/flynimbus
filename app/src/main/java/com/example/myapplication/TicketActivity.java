package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Get the list of selected seats passed from seatchosse activity
        ArrayList<String> selectedSeats = getIntent().getStringArrayListExtra("selectedSeats");

        // Find the TextView where you want to display the selected seats
        TextView seatsTextView = findViewById(R.id.seatsTextView);

        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            // Display the selected seats as a comma-separated string
            StringBuilder seatList = new StringBuilder("Selected Seats: ");
            for (String seat : selectedSeats) {
                seatList.append(seat).append(", ");
            }
            // Remove the last comma and space
            seatList.setLength(seatList.length() - 2);

            seatsTextView.setText(seatList.toString());
        } else {
            seatsTextView.setText("No seats selected.");
            Toast.makeText(this, "No seats selected!", Toast.LENGTH_SHORT).show();
        }
    }
}
