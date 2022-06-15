package com.example.bankapp20;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class updating_money extends AppCompatActivity {

    Button subBtn;
    EditText incInput;
    EditText exInput;

    String incDataUp;
    String expDataUp;
    Integer incDataUpInt;
    Integer expDataUpInt;


    static String incomeView;
    static String expensesView;

    Integer incomeViewInt;
    Integer expensesViewInt;
    Integer sumInt;

    static String amountView;


    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String uid = firebaseAuth.getCurrentUser().getUid();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating_money);

        subBtn = findViewById(R.id.subBtn);
        incInput = findViewById(R.id.incomsInput);
        exInput = findViewById(R.id.expensesInput);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users user = snapshot.getValue(Users.class);
                    if (user.getUid().equals(uid)) {

                        incomeView = (user.getAmount());
                        expensesView = (user.getExpenses());

                        incomeViewInt = Integer.parseInt(incomeView);
                        expensesViewInt = Integer.parseInt(expensesView);
                        sumInt = incomeViewInt - expensesViewInt;
                        amountView = sumInt.toString();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        subBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String expensesT = exInput.getText().toString();
                String incomeT = incInput.getText().toString();

                int a = Integer.parseInt(incomeView);
                int b = Integer.parseInt(incomeT);
                incDataUpInt = a + b;
                incDataUp = incDataUpInt.toString();

                int c = Integer.parseInt(expensesView);
                int d = Integer.parseInt(expensesT);
                expDataUpInt = c + d;
                expDataUp = expDataUpInt.toString();

                HashMap hashMap = new HashMap();
                hashMap.put("expenses", expDataUp);
                hashMap.put("amount", incDataUp);

                reference.child(uid).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(updating_money.this, "Update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, updating_money.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(this, ProfileRetrieve.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}