package com.example.hotelcheckinapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HotelCheckInAdapter extends FirebaseRecyclerAdapter<HotelCheckInModel, HotelCheckInAdapter.HotelViewHolder> {

    public HotelCheckInAdapter(@NonNull FirebaseRecyclerOptions<HotelCheckInModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HotelCheckInAdapter.HotelViewHolder holder, int position, @NonNull HotelCheckInModel model) {
            holder.Name.setText(model.getName());
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
        public HotelViewHolder(@NonNull View itemView)
        {
            super(itemView);

            Name
                    = itemView.findViewById(R.id.Name);
        }
    }
}
