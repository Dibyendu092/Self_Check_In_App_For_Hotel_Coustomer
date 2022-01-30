package com.example.hotelcheckinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class CheckoutActivity extends AppCompatActivity {



    TextView Bookingid;
    TextView CheckOutbt;
    EditText Name;
    EditText CheckoutTime;
    EditText id;




    FirebaseDatabase database1 = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference1 = database1.getReference();

    FirebaseAuth auth1 = FirebaseAuth.getInstance();
    FirebaseUser user1 = auth1.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Bookingid = findViewById(R.id.txtid);
        CheckOutbt = findViewById(R.id.CheckOutTxt);
        Name = findViewById(R.id.ed1);
        CheckoutTime = findViewById(R.id.ed2);
        id = findViewById(R.id.ed3);



        CheckOutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsofCheckOut();
                Toast.makeText(CheckoutActivity.this, "You are Succesfully Check Out.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }

    public void detailsofCheckOut(){

        String userUid1 = user1.getUid();

        String P = id.getText().toString();
        String q = Name.getText().toString();
        String r = CheckoutTime.getText().toString();

        databaseReference1.child("Check Out Details").child(userUid1).child("Booking id:").setValue(P);
        databaseReference1.child("Check Out Details").child(userUid1).child("Name").setValue(q);
        databaseReference1.child("Check Out Details").child(userUid1).child("Check out Time").setValue(r);


    }

}