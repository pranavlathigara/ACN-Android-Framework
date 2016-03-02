package com.accenture.android.framework.context;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.accenture.android.framework.util.ActivityLevelInitializer;
import com.accenture.android.framework.util.BusProvider;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class AcnActivity extends AppCompatActivity {

    private int layoutRes, toolbarRes, statusbarColor;

    public AcnActivity(int layoutRes, int toolbarRes, int statusbarColor){

        this.layoutRes = layoutRes;
        this.toolbarRes = toolbarRes;
        this.statusbarColor = statusbarColor;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }

    private void init(){

        setContentView(this.layoutRes);

        ActivityLevelInitializer.initToolbar(this, this.toolbarRes);
        ActivityLevelInitializer.initButterKnife(this);

        //CHANGE STATUS BAR COLOR
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(getResources().getColor(this.statusbarColor));

    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
