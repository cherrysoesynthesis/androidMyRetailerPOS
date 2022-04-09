package com.dcs.myretailer.app.Cashier;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerItemDiscountAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerItemDiscountAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabFragmentPercentage tabPercentage = new TabFragmentPercentage();
                return tabPercentage;
            case 1:
                TabFragmentAmount tabAmount = new TabFragmentAmount();
                return tabAmount;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}