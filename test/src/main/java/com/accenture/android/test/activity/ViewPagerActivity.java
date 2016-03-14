package com.accenture.android.test.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.view.AcnViewPager;
import com.accenture.android.test.R;
import com.accenture.android.test.fragment.ViewPagerFragment;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;
import com.thefinestartist.utils.etc.PreferenceUtil;

import java.util.ArrayList;

import butterknife.Bind;

public class ViewPagerActivity extends AcnActivity {

    public ViewPagerActivity(){
        super(R.layout.activity_viewpager, R.id.toolbar, R.id.back_button, true, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> tabTitles = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();

        for(int i=1; i<=10; i++){
            tabTitles.add("{fa-android} Tab " + i);

            Bundle bundle = new Bundle();
            bundle.putString("text", "TAB " + i);

            ViewPagerFragment fragment = new ViewPagerFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        acn_viewpager.setContent(fragments, tabTitles);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        PreferenceUtil.put("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_viewpager)
    AcnViewPager acn_viewpager;

}
