package com.example.hotelcheckinapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HotelCheckInAdapter extends FirebaseRecyclerAdapter<HotelCheckInModel, HotelCheckInAdapter.HotelViewHolder> {

    Context context;
    Context context1;
    public HotelCheckInAdapter(@NonNull FirebaseRecyclerOptions<HotelCheckInModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HotelCheckInAdapter.HotelViewHolder holder, int position, @NonNull HotelCheckInModel model) {
            holder.Name.setText(model.getName());
            context = holder.itemView.getContext();
            context1 = holder.itemView.getContext();

            holder.Checkin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, HotelCheckInActivity.class);
                    context.startActivity(i);
                }
            });
            holder.CheckOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), HotelCheckOutActivity.class);
                    context1.startActivity(i);
                }
            });
    }

    @NonNull
    @Override
    public HotelCheckInAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_recycler, parent, false);
        return new HotelCheckInAdapter.HotelViewHolder(view);
    }

    class HotelViewHolder extends RecyclerView.ViewHolder{
        TextView Name;
        Button Checkin;
        Button CheckOut;
        public HotelViewHolder(@NonNull View itemView)
        {
            super(itemView);

            Name
                    = itemView.findViewById(R.id.Name);
            Checkin =itemView.findViewById(R.id.CheckInButton);
            CheckOut = itemView.findViewById(R.id.CheckOutButton);
        }
    }
}
