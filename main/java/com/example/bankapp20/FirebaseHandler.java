package com.example.bankapp20;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

public class FirebaseHandler {
    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();



    public static void login(String email, String pass, Context context) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference("Users");
                    Toast.makeText(context, "You are logged in", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(context, updating_money.class);
                    context.startActivity(mainIntent);
                }
                else {
                    Toast.makeText(context, "Error occurred while logging in", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error occurred while logging in", Toast.LENGTH_LONG).show();
            }
        });
    }





    public static void signup(String phone, String amount, String incomes, String expenses, String name,
                              String email, String pass, String lastName, String age, Context context) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("name", name);
                    hashMap.put("uid", uid);
                    hashMap.put("amount", amount);
                    hashMap.put("incomes ", incomes);
                    hashMap.put("expenses", expenses);
                    hashMap.put("lastName", lastName);
                    hashMap.put("age", age);
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference("Users");

                    reference.child(uid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            RegisterActivity.sendSMS(name, email, pass, phone);
                            Toast.makeText(context, "You are regiserd in", Toast.LENGTH_LONG).show();
                            Intent mainIntent = new Intent(context, updating_money.class);
                            context.startActivity(mainIntent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error while logging in", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Toast.makeText(context, "Error occurred while registered", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }



}
