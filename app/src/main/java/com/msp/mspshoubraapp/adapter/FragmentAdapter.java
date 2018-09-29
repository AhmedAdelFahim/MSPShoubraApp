package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.msp.mspshoubraapp.ui.DayFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    Activity activity;

    public FragmentAdapter(FragmentManager fm/*, Activity activity*/) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle;
        switch (position) {
            case 0:
                bundle = new Bundle();
                bundle.putString("day", "Sun");
                fragment = new DayFragment();
                fragment.setArguments(bundle);
                Log.d("QWERTY", "sun");
                return fragment;
            case 1:
                bundle = new Bundle();
                bundle.putString("day", "Mon");
                fragment = new DayFragment();
                fragment.setArguments(bundle);
                Log.d("QWERTY", "mon");
                return fragment;
            case 2:
                //Log.d("ASDFG","11111111");
                bundle = new Bundle();
                bundle.putString("day", "Tue");
                fragment = new DayFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 3:
                bundle = new Bundle();
                bundle.putString("day", "Wed");
                fragment = new DayFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 4:
                bundle = new Bundle();
                bundle.putString("day", "Thu");
                fragment = new DayFragment();
                fragment.setArguments(bundle);
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Sun";
            case 1:
                return "Mon";
            case 2:
                return "Tue";
            case 3:
                return "Wed";
            case 4:
                return "Thu";
        }
        return null;
    }

}
