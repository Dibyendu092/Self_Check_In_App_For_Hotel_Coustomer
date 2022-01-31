package com.example.hotelcheckinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MariotCheckInCheckOutDashBoard extends AppCompatActivity {

    TextView CheckIn;
    TextView CheckOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mariot_check_in_check_out_dash_board);
        CheckIn = findViewById(R.id.txtcheckin);
        CheckOut = findViewById(R.id.txtcheckout);

        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MariotCheckInCheckOutDashBoard.this, MarriotCheckIn.class);
                startActivity(i);





            }
        });
        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MariotCheckInCheckOutDashBoard.this, MariotCheckOut.class);
                startActivity(i);


            }
        });

    }
}