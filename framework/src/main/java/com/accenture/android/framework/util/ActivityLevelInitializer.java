package com.accenture.android.framework.util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class ActivityLevelInitializer {

    public static void initToolbar(final AppCompatActivity activity, int toolbarRes) {

        final Toolbar toolbar = (Toolbar) activity.findViewById(toolbarRes);
        activity.setSupportActionBar(toolbar);
        try {
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        activity.getSupportActionBar().setDisplayShowCustomEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setContentInsetsAbsolute(0, 0);

    }

    public static void initButterKnife(final AppCompatActivity activity) {

        ButterKnife.bind(activity);

    }

}
