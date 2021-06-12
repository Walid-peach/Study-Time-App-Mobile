package com.example.studytime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dash extends AppCompatActivity {
    ImageButton imageButton;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    LottieAnimationView lottie_power;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        lottie_power = findViewById(R.id.lottie_power);
        lottie_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dash.this,MainActivity.class));
            }
        });




        imageButton =(ImageButton)findViewById(R.id.imageButton);
        imageButton2 =(ImageButton)findViewById(R.id.imageButton2);
        imageButton3 =(ImageButton)findViewById(R.id.imageButton3);
        imageButton4 =(ImageButton)findViewById(R.id.imageButton4);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Dash.this, profil.class);
                startActivity(intentLoadNewActivity);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Dash.this,Salle.class);
                startActivity(intentLoadNewActivity);
            }

        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Dash.this, manage.class);
                startActivity(intentLoadNewActivity);
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Dash.this, notif.class);
                startActivity(intentLoadNewActivity);
            }
        });

    }
}
