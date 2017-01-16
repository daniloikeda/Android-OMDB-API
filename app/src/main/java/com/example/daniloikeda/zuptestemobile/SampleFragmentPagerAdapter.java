package com.example.daniloikeda.zuptestemobile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by DaniloIkeda on 09/01/2017.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String titles)
    {
        this.fragments.add(fragment);
        this.titles.add(titles);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}