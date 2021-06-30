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

import java.util.List;

public class MytimeSlotAdapter extends RecyclerView.Adapter<MytimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;

    public MytimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @NotNull
    @Override
    public MytimeSlotAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.activity_time_slot, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MytimeSlotAdapter.MyViewHolder holder, int position) {
        holder.txt_time_slot.setText(new SpannableStringBuilder(Common.converTimeSlotToString(position).toString()));
        if(timeSlotList.size()==0){
            holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
            holder.txt_time_aviable.setText("Non Disponible");
            holder.txt_time_aviable.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));

        }
        else
        {
            for(TimeSlot slotValue:timeSlotList){
                int slot = Integer.parseInt(slotValue.getSlot().toString());
                if(slot== position)
                {
                    holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));

                    holder.txt_time_aviable.setText("occupé");
                    holder.txt_time_aviable.setTextColor(context.getResources().getColor(android.R.color.white));
                    holder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.white));



                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return 24;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_time_slot, txt_time_aviable;
        CardView card_time_slot;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            card_time_slot = (CardView)itemView.findViewById(R.id.card_time_slot);
            txt_time_slot =(TextView)itemView.findViewById(R.id.txt_time_slot);
            txt_time_aviable = (TextView)itemView.findViewById(R.id.txt_time_aviable);
        }
    }

}
