package com.example.hotelcheckinapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HotelMailSActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{


    String[] country = { "Select Country","West Bengal", "USA", "China", "Japan", "Other"};

    String[] State = {"Select State", "Kolkata", "Delhi", "Pune"};
    String defaultTextForSpinner = "Select Country";
    String defaultTextForSpinner1 = "Select State";

    String SelectedState;
    String SelectedCountry;

    Button SearchButton;
    private int cnt=0;

    private RecyclerView recyclerView;
    HotelCheckInAdapter adapter;

    DatabaseReference mbase;

    FirebaseAuth auth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_mail_sactivity);


        SearchButton = findViewById(R.id.SearchButton);
        recyclerView = findViewById(R.id.recycler_view);


        Spinner spin = (Spinner) findViewById(R.id.Spinnercountry);
        spin.setAdapter(new CoustomAdapterSppiner(this, R.layout.spinner_layout, country, defaultTextForSpinner));
        spin.setOnItemSelectedListener(this);

        Spinner spin1 = (Spinner) findViewById(R.id.Spinnerstate);
        spin1.setAdapter(new CoustomAdapterSppiner(this, R.layout.spinner_layout, State, defaultTextForSpinner1));
        spin1.setOnItemSelectedListener(this);



        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,State);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin1.setAdapter(bb);



        SearchButton.setOnClickListener(new View.OnClickListener() {
            private Object error;

            @Override
            public void onClick(View view) {
                if (SelectedCountry == null || SelectedState == null) {
                    Toast.makeText(HotelMailSActivity.this, "Please Chose Appropiate State And Country:", Toast.LENGTH_SHORT).show();
                } else {
                    mbase = FirebaseDatabase.getInstance().getReference().child("Hotel").child(SelectedCountry).child(SelectedState);
                    mbase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot childSnapshot: snapshot.getChildren()){
                                cnt++;
                            }
                            if(cnt!=0) {
                                recyclerView.setLayoutManager(new LinearLayoutManager(HotelMailSActivity.this));

                                FirebaseRecyclerOptions<HotelCheckInModel> options =
                                        new FirebaseRecyclerOptions.Builder<HotelCheckInModel>()
                                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child(SelectedCountry).child(SelectedState).orderByChild("Name"), HotelCheckInModel.class)
                                                .build();

                                adapter = new HotelCheckInAdapter(options);
                                adapter.startListening();
                                recyclerView.setAdapter(adapter);
                            }else{
                                Toast.makeText(HotelMailSActivity.this, "Please Select Correct City Under Correct State", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        Spinner spin = (Spinner)arg0;
        Spinner spin1 = (Spinner)arg0;
        if(spin.getId() == R.id.Spinnercountry){

            if(position >0){
                SelectedCountry = country[position];
            }

        }
        else if(spin1.getId() == R.id.Spinnerstate){
            if(position >0){
                SelectedState = State[position];
            }

        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Choose Countries :", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hotelcheckinmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                showAbout();
                return true;
            case R.id.signOut:
                auth.signOut();
                Intent i = new Intent(HotelMailSActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAbout() {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}