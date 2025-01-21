package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerTo, spinnerAdult, spinnerChild, spinnerSeatClass, spinnerDate;
    private Map<String, Integer> seatClassPrices = new HashMap<>(); // Map to store seat class prices
    private TextView textViewEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize the TextView for displaying email
        textViewEmail = findViewById(R.id.textViewEmail);

        // Get the current user
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Check if the user is logged in
        if (currentUser != null) {
            // Display the user's email
            String email = currentUser.getEmail();
            textViewEmail.setText("Logged in as: " + email);
        } else {
            // Handle case when the user is not logged in
            textViewEmail.setText("Not logged in");
        }

        // Initialize spinners
        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);
        spinnerAdult = findViewById(R.id.spinnerAdult);
        spinnerChild = findViewById(R.id.spinnerChild);
        spinnerSeatClass = findViewById(R.id.spinnerSeatClass);
        spinnerDate = findViewById(R.id.spinnerDate);

        // Initialize seat class prices (example prices)
        seatClassPrices.put("Economy", 100);   // Economy price
        seatClassPrices.put("Business", 300);  // Business price
        seatClassPrices.put("First Class", 600); // First Class price

        // Adapter for Country list (From and To spinners)
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this, R.array.country_list, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter for 'From' and 'To' spinners
        spinnerFrom.setAdapter(countryAdapter);
        spinnerTo.setAdapter(countryAdapter);

        // Adapter for Passenger count (Adult and Children spinners)
        ArrayAdapter<CharSequence> passengerAdapter = ArrayAdapter.createFromResource(this, R.array.passenger_count, android.R.layout.simple_spinner_item);
        passengerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter for adult and child passenger spinners
        spinnerAdult.setAdapter(passengerAdapter);
        spinnerChild.setAdapter(passengerAdapter);

        // Adapter for Seat Class (Economy, Business, First Class)
        ArrayAdapter<CharSequence> seatClassAdapter = ArrayAdapter.createFromResource(this, R.array.seat_classes, android.R.layout.simple_spinner_item);
        seatClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeatClass.setAdapter(seatClassAdapter);

        // Adapter for Date (you can use a date format or predefined list)
        List<String> dateList = new ArrayList<>();
        dateList.add("Select Date");
        dateList.add("2025-01-15");
        dateList.add("2025-01-16");
        dateList.add("2025-01-17");
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateList);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(dateAdapter);

        // Set up listeners for spinners
        setSpinnerListener(spinnerFrom, "From");
        setSpinnerListener(spinnerTo, "To");
        setSpinnerListener(spinnerAdult, "Adults");
        setSpinnerListener(spinnerChild, "Children");
        setSpinnerListener(spinnerSeatClass, "Seat Class");
        setSpinnerListener(spinnerDate, "Date");

        // Button setup for Submit action
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> {
            // Get the selected values from the spinners
            String fromValue = spinnerFrom.getSelectedItem().toString();
            String toValue = spinnerTo.getSelectedItem().toString();
            String selectedSeatClass = spinnerSeatClass.getSelectedItem().toString();
            int seatClassPrice = seatClassPrices.getOrDefault(selectedSeatClass, 0);

            // Check if a valid seat class is selected (not "Select Seat Class")
            if (!selectedSeatClass.equals("Select Seat Class")) {
                // Create an Intent to navigate to AirlinesChose activity
                Intent intent = new Intent(MainActivity.this, Airlineschose.class);
                // Pass selected values to the AirlinesChose activity
                intent.putExtra("from_value", fromValue);
                intent.putExtra("to_value", toValue);
                intent.putExtra("selectedSeatClass", selectedSeatClass);
                intent.putExtra("price", seatClassPrice);
                // Start the AirlinesChose activity
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please select a seat class", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Helper method to set item selected listeners for spinners.
     *
     * @param spinner       The spinner to which listener is to be set
     * @param label         Label to display in the Toast (From, To, Adults, Children, Seat Class)
     */
    private void setSpinnerListener(Spinner spinner, final String label) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();
                // Avoid showing Toast for the initial 'Select' option
                if (!selectedItem.equals("Select Country") && !selectedItem.equals("Select Passenger Count") && !selectedItem.equals("Select Seat Class") && !selectedItem.equals("Select Date")) {
                    Toast.makeText(MainActivity.this, label + ": " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when no selection is made
            }
        });
    }
}
