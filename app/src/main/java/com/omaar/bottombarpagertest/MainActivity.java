package com.omaar.bottombarpagertest;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.omaar.bottombarpager.BottomBarPagerDataHolder;
import com.omaar.bottombarpager.BottomBarPager;

/**
 * The MainActivity for testing the library
 */
public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize three animated vectors
        AnimatedVectorDrawable places_icon = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.places_animated_icon);
        AnimatedVectorDrawable metro_icon = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.metro_animated_icon);
        AnimatedVectorDrawable metro_icon1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.metro_animated_icon);

        //create a new bottom app bar
        BottomBarPagerDataHolder dataHolder = new BottomBarPagerDataHolder();

        //initialize the three vectors
        dataHolder.setTab1_vector(places_icon);
        dataHolder.setTab2_vector(metro_icon);
        dataHolder.setTab3_vector(metro_icon1);

        //initialize the three fragments
        dataHolder.setFragment1(new FragOne());
        dataHolder.setFragment2(new FragTwo());
        dataHolder.setFragment3(new FragThree());

        //initialize the three titles
        dataHolder.setTab1_title("PLACES");
        dataHolder.setTab2_title("STATIONS");
        dataHolder.setTab3_title("DIRECTIONS");

        //initialize UI object with the data holder
        BottomBarPager bottomAppBarThreeItems = findViewById(R.id.tabs);
        bottomAppBarThreeItems.initializeAppBar(dataHolder);

    }

}