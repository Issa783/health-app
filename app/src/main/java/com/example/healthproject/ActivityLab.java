package com.example.healthproject;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityLab extends AppCompatActivity {
    private DatabaseReference databaseReference;

    private EditText fullNameEditText;
    private EditText phoneNumberEditText;
    private EditText addressEditText;

    private Button bookButton;
    private Button selectDateButton;
    private Button selectTimeButton;

    private int selectedYear, selectedMonth, selectedDay;
    private int selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        // Initialize Views
        fullNameEditText = findViewById(R.id.fullNameEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        addressEditText = findViewById(R.id.addressEditText);

        bookButton = findViewById(R.id.bookButton);
        selectDateButton = findViewById(R.id.dateButton);
        selectTimeButton = findViewById(R.id.timeButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("lab_tests");

        // Set up OnClickListener for the Book button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the lab test booking logic here
                bookLabTest();
            }
        });

        // Set up OnClickListener for the Select Date button
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Set up OnClickListener for the Select Time button
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        // Add any other initialization code or logic needed for the lab test activity
    }

    private void bookLabTest() {
        // Get data from EditText fields
        String fullName = fullNameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        // Perform validation
        if (fullName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            // Display an error message if any of the fields are empty
            Toast.makeText(ActivityLab.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return; // Exit the method to prevent data from being stored in the database
        }

        // You can add more complex validation logic if needed

        // Create a LabTest object with the user's input
        LabTest labTest = new LabTest(fullName, phoneNumber, address, selectedDay, selectedMonth, selectedYear, selectedHour, selectedMinute);

        // Push the LabTest object to Firebase Database
        String key = databaseReference.push().getKey(); // Generates a unique key for each entry
        databaseReference.child(key).setValue(labTest);

        // Display a confirmation message with selected date and time
        String confirmationMessage = "Lab Test booked!\n\n" +
                "Full Name: " + fullName + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Address: " + address + "\n" +
                "Date: " + selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear + "\n" +
                "Time: " + selectedHour + ":" + selectedMinute;

        Toast.makeText(ActivityLab.this, confirmationMessage, Toast.LENGTH_LONG).show();
    }

    private void showDatePicker() {
        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = month;
                selectedDay = dayOfMonth;
                // Update the button text or perform any other action with the selected date
                selectDateButton.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        // Get current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create and show TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedHour = hourOfDay;
                selectedMinute = minute;
                // Update the button text or perform any other action with the selected time
                selectTimeButton.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}
