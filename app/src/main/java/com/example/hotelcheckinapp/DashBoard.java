package com.example.hotelcheckinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoard extends AppCompatActivity {

    TextView tajBengal;
    TextView Itc;
    TextView GoldenPark;
    TextView Auaris;
    TextView Marriot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        tajBengal = findViewById(R.id.ht1);
        Itc = findViewById(R.id.ht2);
        GoldenPark = findViewById(R.id.ht3);
        Auaris = findViewById(R.id.ht4);
        Marriot = findViewById(R.id.ht5);

        tajBengal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(DashBoard.this, ChecckinCheckoutDashBoard.class);
                startActivity(i);

            }
        });
        Itc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DashBoard.this, itcCheckinCheckOutDashBoard.class);
                startActivity(i);
            }
        });
        GoldenPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DashBoard.this, GoldenParkCheckInCheckoutDashBoard.class);
                startActivity(i);
            }
        });
        Auaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard.this, AurisCheckInCheckOutDashBoard.class);
                startActivity(i);

            }
        });
        Marriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard.this, MariotCheckInCheckOutDashBoard.class);
                startActivity(i);

            }
        });
    }
}