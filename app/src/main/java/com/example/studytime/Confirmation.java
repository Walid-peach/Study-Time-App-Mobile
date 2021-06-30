package com.example.studytime;



import android.app.Activity;
import android.app.Dialog;

import android.widget.TextView;

public class Confirmation extends Dialog {

    private TextView text;

    public Confirmation(Activity activity) {
        super (activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.activity_confirmation);

        this.text = findViewById(R.id.textconfiPlace);

    }
    public void set_text(String title){
        this.text = text;
    }

}