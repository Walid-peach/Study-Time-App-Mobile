package com.example.studytime;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyViewPagerAdapter  extends FragmentPagerAdapter {
    public MyViewPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return BookingStep1Fragment.getInstance();


            case 1 :
                return BookingStep2Fragment.getInstance();
            case 2 :
                return BookingStep3Fragment.getInstance();

            case 3 :
                return BookingStep4Fragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
