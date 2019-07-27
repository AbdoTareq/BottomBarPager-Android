package com.omaar.bottombarpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * The Adapter class used to put the fagments into the view pager
 */
public class ViewPagerAadpter extends FragmentPagerAdapter {

    //create fragment objects
    private Fragment f1, f2, f3;

    public ViewPagerAadpter(FragmentManager fm, Fragment f1, Fragment f2, Fragment f3) {
        super(fm);

        //initialize fragment one
        if (f1 == null)
            this.f1 = new Fragment();
        else
            this.f1 = f1;

        //initialize fragment two
        if (f2 == null)
            this.f2 = new Fragment();
        else
            this.f2 = f2;

        //initialize fragment three
        if (f3 == null)
            this.f3 = new Fragment();
        else
            this.f3 = f3;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // First Fragment
                return f1;
            case 1: // Second Fragment
                return f2;
            case 2: // Third Fragment
                return f3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
