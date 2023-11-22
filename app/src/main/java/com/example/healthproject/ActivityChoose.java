package com.example.healthproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class ActivityChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        // Get the username from the intent if passed from LoginActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USERNAME")) {
            String username = intent.getStringExtra("USERNAME");
            updateWelcomeMessage(username);
        }

        // Assuming you have buttons with the IDs buttonBookAppointment and buttonLabTest in your layout
        // You can customize this based on your actual layout
        Button buttonBookAppointment = findViewById(R.id.buttonBookAppointment);
        Button buttonLabTest = findViewById(R.id.buttonLabTest);

        // Set up OnClickListener for the Book Appointment button
        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Book Appointment activity
                startActivity(new Intent(ActivityChoose.this, ActivityBookAppointment.class));
            }
        });

        // Set up OnClickListener for the Lab Test button
        buttonLabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Lab Test activity
                startActivity(new Intent(ActivityChoose.this, ActivityLab.class));
            }
        });

        // Assuming you have a Button with the ID buttonLogout in your layout
        // You can customize this based on your actual layout
        Button buttonLogout = findViewById(R.id.buttonLogout);

        // Set up OnClickListener for the Logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add any logout logic here
                // For example, return to the login activity
                startActivity(new Intent(ActivityChoose.this, LoginActivity.class));
                finish(); // Close the current activity
            }
        });
    }

    private void updateWelcomeMessage(String username) {
        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Welcome, " + username + "!");
    }
}
