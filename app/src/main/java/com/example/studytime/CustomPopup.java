package com.example.studytime;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomPopup extends Dialog {


    private String title;
    private TextView titleview;
    private Button p1,p2,p3,p4,p5,p6,p7,p8;




    public CustomPopup(Activity activity){
        super (activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.my_popup);
        this.title = "Table numero";
        this.titleview = findViewById(R.id.tableid);
        this.p1 = findViewById(R.id.place1);
        this.p2 = findViewById(R.id.place2);
        this.p3 = findViewById(R.id.place3);
        this.p4 = findViewById(R.id.place4);
        this.p5 = findViewById(R.id.place5);
        this.p6 = findViewById(R.id.place6);
        this.p7 = findViewById(R.id.place7);
        this.p8 = findViewById(R.id.place8);

    }
    public void set_title(String title){
        this.title = title;
    }

    public Button getP1(){
        return p1;
    }
    public Button getP2(){
        return p2;
    }
    public Button getP3(){
        return p3;
    }
    public Button getP4(){
        return p4;
    }
    public Button getP5(){
        return p5;
    }
    public Button getP6(){
        return p6;
    }
    public Button getP7(){
        return p7;
    }
    public Button getP8(){
        return p8;
    }
    

    public void build() {
        show();
        titleview.setText(title);
    }



}

