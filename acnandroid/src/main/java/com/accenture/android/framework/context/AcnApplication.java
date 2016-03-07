package com.accenture.android.framework.context;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.accenture.android.framework.util.AppLevelInitializer;
import com.accenture.android.framework.util.Config;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public abstract class AcnApplication extends Application {

    public AcnApplication(String appFont, String loggerTag, int imageFadeInDuration){

        Config.appFont = appFont;
        Config.loggerTag = loggerTag;
        Config.imageFadeInDuration = imageFadeInDuration;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.init();
    }

    private void init(){

        AppLevelInitializer.initLogger(Config.loggerTag);
        AppLevelInitializer.initSharedPrefs(this);
        AppLevelInitializer.initIconify();
        AppLevelInitializer.initAppFont(Config.appFont);
        AppLevelInitializer.initImageLoader(this, Config.imageFadeInDuration);
        AppLevelInitializer.initInternetListener(this);

    }

    //REQUIRED FOR MULTIDEX
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
