package com.accenture.android.framework.context;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.accenture.android.framework.util.AppLevelInitializer;
import com.accenture.android.framework.util.Config;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class AcnApplication extends Application {

    public AcnApplication(String defaultFont, String loggerTag){

        Config.defaultFont = defaultFont;
        Config.loggerTag = loggerTag;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.init();
    }

    private void init(){

        AppLevelInitializer.initLogger(Config.loggerTag);
        AppLevelInitializer.initIconify();
        AppLevelInitializer.initDefFont(Config.defaultFont);
        AppLevelInitializer.initImageLoader(this);
        AppLevelInitializer.initInternetListener(this);

    }

    //REQUIRED FOR MULTIDEX
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
