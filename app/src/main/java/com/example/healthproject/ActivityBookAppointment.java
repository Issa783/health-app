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

public class ActivityBookAppointment extends AppCompatActivity {

    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    private EditText editTextAddress;

    private Button buttonSelectDate;
    private Button buttonSelectTime;
    private Button buttonSubmit;

    private int selectedYear, selectedMonth, selectedDay;
    private int selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);

        // Initialize Views
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAddress = findViewById(R.id.editTextAddress);

        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonSelectTime = findViewById(R.id.buttonSelectTime);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Set up OnClickListener for the Select Date button
        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Set up OnClickListener for the Select Time button
        buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        // Set up OnClickListener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from EditText fields
                String fullName = editTextFullName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();

                // Validate input

                // Display a confirmation message with selected date and time
                String confirmationMessage = "Appointment booked!\n\n" +
                        "Full Name: " + fullName + "\n" +
                        "Phone Number: " + phoneNumber + "\n" +
                        "Address: " + address + "\n" +
                        "Date: " + selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear + "\n" +
                        "Time: " + selectedHour + ":" + selectedMinute;

                Toast.makeText(ActivityBookAppointment.this, confirmationMessage, Toast.LENGTH_LONG).show();
            }
        });
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
                buttonSelectDate.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
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
                buttonSelectTime.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}
