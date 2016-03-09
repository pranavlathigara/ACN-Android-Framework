package com.accenture.android.framework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 09.03.2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabTitles;

    public ViewPagerAdapter(AppCompatActivity appCompatActivity, ArrayList<Fragment> fragments, ArrayList<String> tabTitles) {

        super(appCompatActivity.getSupportFragmentManager());

        this.fragments = fragments;
        this.tabTitles = tabTitles;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = fragments.get(position);

        return fragment;

    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return tabTitles.size();
    }

}
