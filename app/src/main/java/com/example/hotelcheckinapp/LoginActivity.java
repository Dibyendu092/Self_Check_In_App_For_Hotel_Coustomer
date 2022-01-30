package com.example.hotelcheckinapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    EditText Email ;
    EditText password;
    TextView SignIn;
    ImageView Gsign;
    TextView Or;
    TextView Singup;
    TextView Forgetpassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    GoogleSignInClient signInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id. ed1);
        password = findViewById(R.id.ed2);
        SignIn = findViewById(R.id.bt1);
        Or = findViewById(R.id.constrain);
        Gsign = findViewById(R.id.bt2);

        Singup = findViewById(R.id.txt2);
        Forgetpassword = findViewById(R.id.txt3);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UEmail = Email.getText().toString();
                String Upassword = password.getText().toString();
                SignWithFirebase(UEmail, Upassword);

            }
        });
        Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);


            }
        });
        Gsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gsign.setClickable(false);
                SignInWithGoogle();

            }
        });
        Forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void SignWithFirebase(String UserMail, String UsserPassword){

        auth.signInWithEmailAndPassword(UserMail, UsserPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(LoginActivity.this, "Succesfully Sign in", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "There is a Problem . Please Try Agaig Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void SignInWithGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client)).requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, gso);
        SignIn();
    }
    public void SignIn(){
        Intent SignInIntent = signInClient.getSignInIntent();
        startActivityForResult(SignInIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            firebaseSignINwithGoogle(task);
        }
    }
    private void firebaseSignINwithGoogle(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            FirebaseEntry(account);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(LoginActivity.this, "You are Succesfully Signed in", Toast.LENGTH_SHORT).show();
            finish();

        } catch (ApiException e) {
            Toast.makeText(LoginActivity.this, "Their is a problem. Please Try Again Later", Toast.LENGTH_SHORT).show();

        }
    }
    private void FirebaseEntry(GoogleSignInAccount account){
        AuthCredential x = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        auth.signInWithCredential(x).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Their is a problem. Please Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}