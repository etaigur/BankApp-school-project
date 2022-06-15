package com.example.bankapp20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passInput;
    private Button loginBtn;
    private TextView signupLink;
    private ProgressDialog loadingBar;
    private Intent backgroundBatmanMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login to your account");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passwordInput);
        signupLink = findViewById(R.id.registerLink);
        loginBtn = findViewById(R.id.submitBtn);


        backgroundBatmanMusic = new Intent(LoginActivity.this, BatmanBackgroundMusic.class);
        startService(backgroundBatmanMusic);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String pass = passInput.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailInput.setError("Email is not in the correct format");
                    emailInput.setFocusable(true);
                } else {
                    FirebaseHandler.login(email, pass, LoginActivity.this);
                }
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(backgroundBatmanMusic);
        super.onDestroy();
    }
}