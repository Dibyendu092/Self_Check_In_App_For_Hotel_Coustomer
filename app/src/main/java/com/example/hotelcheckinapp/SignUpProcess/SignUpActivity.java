package com.example.hotelcheckinapp.SignUpProcess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelcheckinapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    TextView Singup;
    ProgressBar progressBar;
    FirebaseAuth auth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Email = findViewById(R.id.ed1);
        Password = findViewById(R.id.ed2);
        Singup = findViewById(R.id.bt1);
        progressBar = findViewById(R.id.pb1);
        progressBar.setVisibility(View.INVISIBLE);

        Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String umail = Email.getText().toString();
                String upassword = Password.getText().toString();
                singupfirebase(umail,upassword);
            }
        });
    }

    public void singupfirebase(String userEmail , String UserPassword){

        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(userEmail,UserPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Your Account createdSuccesfully", Toast.LENGTH_SHORT).show();
                    finish();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Their is a Problem Try again later", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}