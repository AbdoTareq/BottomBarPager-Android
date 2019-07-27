package com.omaar.bottombarpager;

import android.graphics.drawable.AnimatedVectorDrawable;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class BottomBarPagerDataHolder {

    ViewPager viewPager ;

    AnimatedVectorDrawable tab1_vector;
    AnimatedVectorDrawable tab2_vector;
    AnimatedVectorDrawable tab3_vector;

    String tab1_title;
    String tab2_title;
    String tab3_title;

    Fragment fragment1 ;
    Fragment fragment2 ;
    Fragment fragment3 ;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public AnimatedVectorDrawable getTab1_vector() {
        return tab1_vector;
    }

    public void setTab1_vector(AnimatedVectorDrawable tab1_vector) {
        this.tab1_vector = tab1_vector;
    }

    public AnimatedVectorDrawable getTab2_vector() {
        return tab2_vector;
    }

    public void setTab2_vector(AnimatedVectorDrawable tab2_vector) {
        this.tab2_vector = tab2_vector;
    }

    public AnimatedVectorDrawable getTab3_vector() {
        return tab3_vector;
    }

    public void setTab3_vector(AnimatedVectorDrawable tab3_vector) {
        this.tab3_vector = tab3_vector;
    }

    public String getTab1_title() {
        return tab1_title;
    }

    public void setTab1_title(String tab1_title) {
        this.tab1_title = tab1_title;
    }

    public String getTab2_title() {
        return tab2_title;
    }

    public void setTab2_title(String tab2_title) {
        this.tab2_title = tab2_title;
    }

    public String getTab3_title() {
        return tab3_title;
    }

    public void setTab3_title(String tab3_title) {
        this.tab3_title = tab3_title;
    }

    public Fragment getFragment1() {
        return fragment1;
    }

    public void setFragment1(Fragment fragment1) {
        this.fragment1 = fragment1;
    }

    public Fragment getFragment2() {
        return fragment2;
    }

    public void setFragment2(Fragment fragment2) {
        this.fragment2 = fragment2;
    }

    public Fragment getFragment3() {
        return fragment3;
    }

    public void setFragment3(Fragment fragment3) {
        this.fragment3 = fragment3;
    }
}
