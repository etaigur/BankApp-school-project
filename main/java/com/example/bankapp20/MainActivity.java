package com.example.bankapp20;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String uid = firebaseAuth.getCurrentUser().getUid();

    private NetworkStatusReceiver networkStatusReceiver = new NetworkStatusReceiver();

    static TextView amountView;
    static TextView incomeView;
    static TextView expensesView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountView = findViewById(R.id.amountNum);
        incomeView = findViewById(R.id.incomesNum);
        expensesView = findViewById(R.id.expensesNum);


        incomeView.setText(updating_money.incomeView);
        expensesView.setText(updating_money.expensesView);
        amountView.setText(updating_money.amountView);
    }




    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStatusReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkStatusReceiver);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
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

