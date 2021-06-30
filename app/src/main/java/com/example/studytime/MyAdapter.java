package com.example.studytime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> list;
    private MyAdapter activity;

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
        View v = LayoutInflater.from(context).inflate(R.layout.activity_time_slot, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {


        holder.txt_time_slot.setText(Common.converTimeSlotToString(position));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                //v.getContext().startActivity(new Intent(v.getContext(), Confirmation.class));
                //Confirmation customPopup = new Confirmation(activity);
                //customPopup.set_text("Table 1");
                //showPopup(v);


                alert("Voulez-vous Continuer ?",v);

            }

            private void alert(String message,View v) {
                LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_confirmation,null);
                TextView textplace = layout.findViewById(R.id.textplace);
                TextView texttime = layout.findViewById(R.id.texttime);
                textplace.setText("Salle "+Salle.setChosen()+" Table : "+Booking.getTable()+" Place : "+Booking.getPlace());

                texttime.setText("le "+removeLastChar(TimeActivity.setDate())+" de "+Common.converTimeSlotToString(position));
                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage(message);
                alertbox.setView(layout);
                alertbox.setCancelable(true);
                alertbox.setPositiveButton(
                        "OUI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Toast.makeText(context, "Votre reservation est bien confirmée ", Toast.LENGTH_SHORT).show();

//                                NotificationCompat.Builder builder = new NotificationCompat.Builder(
//                                        v.getContext()
//                                ).setSmallIcon(R.drawable.studylogo)
//                                        .setContentTitle("Nouvelle Reservation")
//                                        .setContentText("Votre reservation est bien confirmée ")
//                                        .setAutoCancel(true)
//                                        ;

                               // builder.setContentIntent(pendingIntent);
                                //NotificationManager notificationManager = (NotificationManager)
                                  //      context.getSystemService(Context.NOTIFICATION_SERVICE);
//                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(v.getContext());
//                                notificationManager.notify(123,builder.build());

                                v.getContext().startActivity(new Intent(v.getContext(), Splashscreen_loading.class));
                                dialog.cancel();
                            }
                        });

                alertbox.setNegativeButton(
                        "NON",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
//                alertbox.setNeutralButton("OUI",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        //to do
//                }});
//                alertbox.setNeutralButton("NON",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        //to do
//                    }});
                alertbox.show();
            }


        });


    }


    public static String  removeFirstandLast(String str)
    {

        // Creating a StringBuilder object
        StringBuilder sb = new StringBuilder(str);

        // Removing the last character
        // of a string

        sb.deleteCharAt(str.length() - 1);




        // Removing the first character
        // of a string
        //sb.deleteCharAt(0);

        // Converting StringBuilder into a string
        // and return the modified string
        return sb.toString();
    }
    private String removeLastChar(String s)
    {
        //returns the string after removing the last character
        return s.substring(0, s.length() - 14);
    }



    @Override
    public int getItemCount() {
        return 24;
    }
}




















