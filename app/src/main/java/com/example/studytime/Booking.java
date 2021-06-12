package com.example.studytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Booking extends AppCompatActivity {

    private Button t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    private Booking activity;
    private TextView txt;

    private static String  place, table;


    public static String getPlace() {
        return place;
    }

    public static String getTable(){
        return table;
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        this.activity=this;

        txt = findViewById(R.id.textNom_du_salle);
        txt.setText(Salle.setChosen());

        //Toast.makeText(Booking.this,"lalalalala", Toast.LENGTH_LONG).show();
        List<String> salles = new ArrayList<>();
        //salles.add("lololo");



        Log.d("My activity", "Salle : " +salles);
        //txttest= salles.get(0);
        //Toast.makeText(Booking.this,"something went wrong! Try again", Toast.LENGTH_LONG).show();


        t1 = (Button) findViewById(R.id.table1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 1");
                table = "t1";
                Toast.makeText(getApplicationContext(),getTable(),Toast.LENGTH_SHORT).show();
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }

        });

        t2 = (Button) findViewById(R.id.table2);
        //t2.setText(txt);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 2");
                table ="t2";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t3 = (Button) findViewById(R.id.table3);
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 3");
                table = "t3";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t4 = (Button) findViewById(R.id.table4);
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 4");
                table = "t4";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t5 = (Button) findViewById(R.id.table5);
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 5");
                table = "t5";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t6 = (Button) findViewById(R.id.table6);
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 6");
                table ="t6";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t7 = (Button) findViewById(R.id.table7);
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 7");
                table = "t7";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t8 = (Button) findViewById(R.id.table8);
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 8");
                table = "t8";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t9 = (Button) findViewById(R.id.table9);
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 9");
                table = "t9";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });
        t10 = (Button) findViewById(R.id.table10);
        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopup customPopup = new CustomPopup(activity);
                customPopup.set_title("Table 10");
                table = "t10";
                customPopup.build();
                customPopup.getP1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p1";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();

                        customPopup.dismiss();
                    }
                });
                customPopup.getP2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p2";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p3";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP4().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p4";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP5().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p5";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP6().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p6";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP7().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p7";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
                customPopup.getP8().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        place = "p8";
                        Intent i = new Intent(Booking.this, TimeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),getPlace(),Toast.LENGTH_SHORT).show();
                        customPopup.dismiss();
                    }
                });
            }
        });




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Salle").document("Principale").collection("Branch")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List <DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot snapshot:snapshotList){
                    //Log.d("My activity","onSuccess" + snapshot.getString("name"));
                    String test = snapshot.getString("name");
                    if (test instanceof  String){
                        Log.d("My activity","onSuccess  " + test);
                    }
                    salles.add(test);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }



}