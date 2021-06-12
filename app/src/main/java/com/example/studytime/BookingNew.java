package com.example.studytime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingNew extends AppCompatActivity {
    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_previeus_step)
    Button btn_previeus_step;
    @BindView(R.id.btn_Next_step)
    Button btn_Next_step;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingnew);
        ButterKnife.bind(BookingNew.this);
        
        setupStepView();
        setColorButton();

        //
         viewPager.setAdapter(new MyViewPagerAdapter (getSupportFragmentManager()));
         viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {
                if (position==0)
                    btn_previeus_step.setEnabled(false);
                else
                    btn_previeus_step.setEnabled(true);

                setColorButton();
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });
    }

    private void setColorButton() {
        if(btn_Next_step.isEnabled()){
            btn_Next_step.setBackgroundResource(R.color.yelew_1);
        }
        else{
            btn_Next_step.setBackgroundResource(R.color.gris_2);
        }
        if(btn_previeus_step.isEnabled()){
            btn_previeus_step.setBackgroundResource(R.color.yelew_1);
        }
        else{
            btn_previeus_step.setBackgroundResource(R.color.gris_2);
        }
    }


    private void setupStepView() {

        List<String> stepList = new ArrayList<>();
        stepList.add("Table");
        stepList.add("Place");
        stepList.add("l'horaire");
        stepList.add("Confirmer");
        stepView.setSteps(stepList);
    }
}