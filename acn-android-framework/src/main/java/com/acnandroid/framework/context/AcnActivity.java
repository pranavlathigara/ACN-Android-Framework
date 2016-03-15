package com.acnandroid.framework.context;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.acnandroid.framework.util.BusProvider;
import com.acnandroid.framework.view.AcnButton;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.thefinestartist.utils.etc.APILevel;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public abstract class AcnActivity extends AppCompatActivity {

    private int layoutRes;
    private int toolbarRes;
    private Integer backButtonRes = null;
    private boolean backButtonVisible;
    private int statusBarColor;

    public AcnActivity(int layoutRes, int toolbarRes, Integer backButtonRes, boolean backButtonVisible, int statusBarColor){

        this.layoutRes = layoutRes;
        this.toolbarRes = toolbarRes;
        this.backButtonRes = backButtonRes;
        this.backButtonVisible = backButtonVisible;
        this.statusBarColor = statusBarColor;

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
        if(APILevel.requireLollipop())
            getWindow().setStatusBarColor(getResources().getColor(this.statusBarColor));

        //BACK BUTTON
        if(backButtonRes != null){
            AcnButton back_button = (AcnButton) findViewById(backButtonRes);
            if(backButtonVisible) {
                back_button.setVisibility(View.VISIBLE);
                back_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
            else{
                back_button.setVisibility(View.GONE);
            }
        }

    }

    public abstract void onInternetStatusChanged(ConnectivityStatus status);

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
