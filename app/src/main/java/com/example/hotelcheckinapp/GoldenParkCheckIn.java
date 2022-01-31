package com.example.hotelcheckinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class GoldenParkCheckIn extends AppCompatActivity {

    EditText Name;
    EditText Email;
    EditText CheckInTime;
    EditText Phone_no;
    TextView AdhharNo;
    TextView BtCheckIn;
    EditText id;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golden_park_check_in);

        Name = findViewById(R.id.ed1);
        Email = findViewById(R.id.ed2);
        AdhharNo = findViewById(R.id.ed3);
        Phone_no = findViewById(R.id.ed);
        CheckInTime = findViewById(R.id.ed4);
        BtCheckIn = findViewById(R.id.txtcheckin);
        id = findViewById(R.id.txt7);

        final Random myRandom = new Random();

        val = String.valueOf(myRandom.nextInt(1000));
        id.setText(val);


        BtCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detailOfbooking();
                Toast.makeText(GoldenParkCheckIn.this, "Succesfully Booked the Room", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void detailOfbooking(){
        String userUid1 = user.getUid();
        String a = Name.getText().toString();
        String b = Email.getText().toString();
        String c = AdhharNo.getText().toString();
        String d = Phone_no.getText().toString();
        String e = CheckInTime.getText().toString();


        databaseReference.child("India").child("Kolkata").child("GoldenPark").child("Check in Details").child(userUid1).child("Booking id").setValue(val);
        databaseReference.child("India").child("Kolkata").child("GoldenPark").child("Check in Details").child(userUid1).child("Name").setValue(a);
        databaseReference.child("India").child("Kolkata").child("GoldenPark").child("Check in Details").child(userUid1).child("Email").setValue(b);
        databaseReference.child("India").child("Kolkata").child("GoldenPark").child("Check in Details").child(userUid1).child("Addhar No").setValue(c);
        databaseReference.child("India").child("Kolkata").child("GoldenPark").child("Check in Details").child(userUid1).child("Phone No").setValue(d);
        databaseReference.child("India").child("Kolkata").child("GoldenPark").child("Check in Details").child(userUid1).child("Check_in time").setValue(e);

    }

}