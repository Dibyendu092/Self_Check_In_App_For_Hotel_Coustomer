package com.example.hotelcheckinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChecckinCheckoutDashBoard extends AppCompatActivity {

    TextView CheckIn;
    TextView CheckOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checckin_checkout_dash_board);

        CheckIn = findViewById(R.id.txtcheckin);
        CheckOut = findViewById(R.id.txtcheckout);


        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent i = new Intent(ChecckinCheckoutDashBoard.this, Details.class);
                        startActivity(i);





            }
        });
        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(ChecckinCheckoutDashBoard.this, CheckoutActivity.class);
                    startActivity(i);


            }
        });
    }





}