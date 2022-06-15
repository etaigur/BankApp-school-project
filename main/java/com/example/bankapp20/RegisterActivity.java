package com.example.bankapp20;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class RegisterActivity extends AppCompatActivity {

    private TextView loginLink;
    private EditText nameInput;
    private EditText lastNameInput;
    private EditText ageInput;
    private EditText emailInput;
    private EditText passInput;
    private EditText phoneNumber;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Open a new account");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.usernameInput);
        passInput = findViewById(R.id.passwordInput);
        signupBtn = findViewById(R.id.submitBtn);
        loginLink = findViewById(R.id.loginLink);
        lastNameInput = findViewById(R.id.lastNameInput);
        ageInput = findViewById(R.id.ageInput);
        phoneNumber = findViewById(R.id.phoneNumberInput);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumber.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String name = nameInput.getText().toString().trim();
                String pass = passInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();
                String age = ageInput.getText().toString().trim();
                String amount = "0";
                String incomes = "0";
                String expenses = "0";
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailInput.setError("Email is not in the correct format");
                    emailInput.setFocusable(true);
                } else if (pass.length() < 7) {
                    passInput.setError("Password must be longer than 7 characters");
                    passInput.setFocusable(true);
                } else {
                    FirebaseHandler.signup(phone, amount, incomes, expenses, name, email, pass, lastName, age,  RegisterActivity.this);
                }
            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public static void sendSMS(String name, String email, String password, String number){
        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null,name + " has signed up to Bank App, here is his email: " + email + " and password: " + password + " so you can keep track after of his expenses", null, null);
    }
}