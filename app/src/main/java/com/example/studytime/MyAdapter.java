package com.example.studytime;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> list;

    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_time_slot;
        public TextView txt_time_aviable;
        public CardView card_time_slot;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            card_time_slot = itemView.findViewById(R.id.recyclerView);
            txt_time_slot = itemView.findViewById(R.id.txt_time_slot);
            txt_time_aviable = itemView.findViewById(R.id.txt_time_aviable);

        }
    }



    @NonNull
    @NotNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_time_slot,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {

        holder.txt_time_slot.setText(Common.converTimeSlotToString(position));
        holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(R.color.white));

    }

    @Override
    public int getItemCount() {
        return 24;
    }
}



















