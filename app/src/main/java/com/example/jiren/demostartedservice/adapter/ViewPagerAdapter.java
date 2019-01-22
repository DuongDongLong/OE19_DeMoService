package com.example.jiren.demostartedservice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.jiren.demostartedservice.fragment.ListSongFragment;
import com.example.jiren.demostartedservice.fragment.RootFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    static final int NUM_ITEMS = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new ListSongFragment();
        } else {
            return new RootFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
