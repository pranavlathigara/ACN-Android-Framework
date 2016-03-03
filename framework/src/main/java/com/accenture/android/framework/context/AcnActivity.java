package com.accenture.android.framework.context;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.accenture.android.framework.util.ActivityLevelInitializer;
import com.accenture.android.framework.util.BusProvider;
import com.accenture.android.framework.view.AcnButton;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public abstract class AcnActivity extends AppCompatActivity {

    private Integer layoutRes = null;
    private Integer toolbarRes = null;
    private Integer backButtonRes = null;
    private Integer statusbarColor = null;

    public AcnActivity(Integer layoutRes, Integer toolbarRes, Integer backButtonRes, Integer statusbarColor){

        this.layoutRes = layoutRes;
        this.toolbarRes = toolbarRes;
        this.backButtonRes = backButtonRes;
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

        //BACK BUTTON ONCLICK
        if(backButtonRes != null){
            AcnButton back_button = (AcnButton) findViewById(backButtonRes);
            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }

    public abstract void internetCatcher(ConnectivityStatus connectivityStatus);

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
