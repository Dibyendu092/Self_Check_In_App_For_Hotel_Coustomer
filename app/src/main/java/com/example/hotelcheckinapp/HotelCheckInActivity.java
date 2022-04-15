package com.example.hotelcheckinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HotelCheckInActivity extends AppCompatActivity {

    TextView HotelName;
    String Hname;
    String OCode;
    EditText Cname;
    EditText CDob;
    EditText CEmail;
    EditText Caddr;
    EditText Cphno;
    EditText CEntryTime;
    EditText Cdate;
    EditText Caddhar;
    EditText Capproxch;
    Button Submit;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_check_in);
        HotelName = findViewById(R.id.HotelName);
        Cname = findViewById(R.id.CoustomerName);
        CDob = findViewById(R.id.CoustomerDateOfBirth);
        CEmail = findViewById(R.id.CoustomerEmail);
        Caddr = findViewById(R.id.CoustomerAddress);
        Cphno = findViewById(R.id.CoustomerPhone);
        CEntryTime = findViewById(R.id.CoustomerEntryTime);
        Cdate = findViewById(R.id.Date);
        Caddhar = findViewById(R.id.AddharCardNo);
        Capproxch = findViewById(R.id.CoustomerApproxCheckoutTime);
        Submit = findViewById(R.id.ButtonCheckInSubmit);

        Intent intent = getIntent();
        Hname = intent.getStringExtra("Hotelname");
        HotelName.setText(Hname);
        OCode = intent.getStringExtra("OrganizationalCode");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Cname.getText().toString().contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Name cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(CEmail.getText().toString().contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(CDob.getText().toString().contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Date Of Birth cannon be empty",Toast.LENGTH_SHORT).show();
                }else if(Caddr.getText().toString().contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Address cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(Cphno.getText().toString().contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Phone No cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(CEntryTime.getText().toString().contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Entry Time Cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(Cdate.getText().toString().contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "date cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(Caddhar.getText().toString().contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Addhar No cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(Capproxch.getText().toString().contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Approximate Check Out Time cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendDetails();
                    Toast.makeText(HotelCheckInActivity.this, "Succesfully Check In", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HotelCheckInActivity.this, HotelMailSActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    public void sendDetails(){
        String User = Cname.getText().toString();
        String UserEmail = CEmail.getText().toString();
        String UserDob = CDob.getText().toString();
        String UserAddr = Caddr.getText().toString();
        String UserPh = Cphno.getText().toString();
        String UserEntryTime = CEntryTime.getText().toString();
        String UserDate = Cdate.getText().toString();
        String UseAddhar = Caddhar.getText().toString();
        String UserApprox = Capproxch.getText().toString();

        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Email").setValue(UserEmail);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Date Of Birth").setValue(UserDob);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Addtres").setValue(UserAddr);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Phone No").setValue(UserPh);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Entry Time").setValue(UserEntryTime);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Date").setValue(UserDate);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Addhar CardNo").setValue(UseAddhar);
        databaseReference.child("Hotel").child("CheckInDetails").child(OCode).child(User).child("Approximate Check Out Time").setValue(UserApprox);
    }

}