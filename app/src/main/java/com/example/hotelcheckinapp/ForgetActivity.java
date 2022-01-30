package com.example.hotelcheckinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {

    TextView ButtonReset;
    EditText Email;
    ProgressBar pp;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        ButtonReset = findViewById(R.id.btreset);
        Email = findViewById(R.id.ed1);
        pp = findViewById(R.id.pb1);

        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ukEmail = Email.getText().toString();
                forgetpassword(ukEmail);
            }
        });
        pp.setVisibility(View.INVISIBLE);
    }
    public void forgetpassword(String UserMail){
        pp.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(UserMail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetActivity.this, "Sent The reset password link to your registered email", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForgetActivity.this, "Their is a Problem. Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}