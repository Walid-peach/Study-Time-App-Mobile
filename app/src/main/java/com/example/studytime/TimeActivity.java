package com.example.studytime;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.firebase.database.DatabaseReference;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity implements DatePickerListener {



    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<String> list;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        HorizontalPicker picker = (HorizontalPicker) findViewById(R.id.datePicker);
        picker.setListener(this)
                .setDays(40)
                .setOffset(0)
                .setDateSelectedColor(Color.DKGRAY)
                .setDateSelectedTextColor(Color.WHITE)
                .setMonthAndYearTextColor(Color.DKGRAY)
                .setTodayButtonTextColor(getColor(R.color.colorPrimary))
                .setTodayDateTextColor(getColor(R.color.colorPrimary))
                .setTodayDateBackgroundColor(Color.GRAY)
                .setUnselectedDayTextColor(Color.DKGRAY)
                .setDayOfWeekTextColor(Color.DKGRAY)
                .setUnselectedDayTextColor(getResources().getColor(R.color.black))
                .showTodayButton(true)
                .init();
        picker.setBackgroundColor(Color.LTGRAY);
        picker.setDate(new DateTime().plusDays(0));






    }

    @Override
    public void onDateSelected(DateTime dateSelected) {

        Log.i("HorizontalPicker", "Selected date is " + dateSelected.toString());

        LoadTime();

    }

    private void LoadTime() {


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);




        recyclerView.addItemDecoration(new SpacesItemDecoration(8));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

    }

}


















