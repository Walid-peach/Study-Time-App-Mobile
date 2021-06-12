package com.example.studytime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Salle extends AppCompatActivity {

    private Button next;
    private static String chosen;
    LottieAnimationView lottie_power,lottie_home;

    public static String  setChosen() {
        return chosen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle);

        lottie_power = findViewById(R.id.lottie_power);
        lottie_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Salle.this,MainActivity.class));
            }
        });

        lottie_home = findViewById(R.id.lottie_home);
        lottie_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Salle.this, Dash.class);
                startActivity(i);
            }
        });

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setChosen()!="choisisser une Salle"){
                    Intent i = new Intent(Salle.this, GroupSolo.class);
                    i.putExtra("city",setChosen());
                    startActivity(i);
                }
                else {
                    Toast.makeText(Salle.this,"Veuillez choisir une salle", Toast.LENGTH_LONG).show();
                }

            }
        });
        Spinner spinner =  findViewById(R.id.spinner);
        List<String> salles = new ArrayList<>();
        salles.add("choisisser une Salle");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,salles);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String  spinnerValue= parent.getItemAtPosition(position).toString();

                Toast.makeText(getBaseContext(),  spinnerValue, Toast.LENGTH_SHORT).show();
                chosen= spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Salle").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){
                    Toast.makeText(Salle.this,"something went wrong! Try again", Toast.LENGTH_LONG).show();
                }
                for(DocumentSnapshot doc :value){
                    if(doc.get("nom")!= null){
                        salles.add(doc.getString("nom"));
                    }
                }

            }
        });
        //Log.d("My activity", "Salle : " +salles);

    }

}