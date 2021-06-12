package com.example.studytime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class GroupSolo extends AppCompatActivity {
    private Button groupe;
    private Button solo;
    LottieAnimationView lottie_power,lottie_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_solo);
        solo = (Button) findViewById(R.id.solo);
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupSolo.this, Booking.class);
                startActivity(i);
            }
        });


        lottie_power = findViewById(R.id.lottie_power);
        lottie_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(GroupSolo.this,MainActivity.class));
            }
        });

        lottie_home = findViewById(R.id.lottie_home);
        lottie_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupSolo.this, Dash.class);
                startActivity(i);
            }
        });


    }
}