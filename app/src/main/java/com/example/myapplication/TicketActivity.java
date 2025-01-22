package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TicketActivity extends AppCompatActivity {

    private TextView seatsTextView;
    private TextView totalPriceTextView;
    private TextView airlineTextView;
    private TextView seatClassTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Initialize views
        seatsTextView = findViewById(R.id.seatsTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        airlineTextView = findViewById(R.id.airlineTextView);
        seatClassTextView = findViewById(R.id.seatClassTextView);

        // Populate initial data
        updateTicketDetails();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update details every time the activity is revisited
        updateTicketDetails();
    }

    private void updateTicketDetails() {
        // Retrieve selected seats
        ArrayList<String> selectedSeats = getIntent().getStringArrayListExtra("selectedSeats");

        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            StringBuilder seatList = new StringBuilder("Selected Seats: \n");
            for (int i = 0; i < selectedSeats.size(); i++) {
                if (i == selectedSeats.size() - 1) {
                    // Highlight the last selected seat
                    seatList.append("").append(selectedSeats.get(i)).append("\n");
                } else {
                    seatList.append(selectedSeats.get(i)).append("\n");
                }
            }
            seatsTextView.setText(seatList.toString());
        } else {
            seatsTextView.setText("No seats selected.");
            Toast.makeText(this, "No seats selected!", Toast.LENGTH_SHORT).show();
        }

        // Retrieve total price
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);
        totalPriceTextView.setText("Total Price: $" + totalPrice);

        // Retrieve airline and class
        String airline = getIntent().getStringExtra("airline");
        String seatClass = getIntent().getStringExtra("seatClass");

        // Display airline
        if (airline != null) {
            airlineTextView.setText("Airline: " + airline);
        } else {
            airlineTextView.setText("Airline: Not specified");
        }

        // Display seat class
        if (seatClass != null) {
            seatClassTextView.setText("Class: " + seatClass);
        } else {
            seatClassTextView.setText("Class: Not specified");
        }
    }

}
