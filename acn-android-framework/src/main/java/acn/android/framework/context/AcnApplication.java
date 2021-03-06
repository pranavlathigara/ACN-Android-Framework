package acn.android.framework.context;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.nostra13.universalimageloader.core.ImageLoader;

import acn.android.framework.util.Config;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public abstract class AcnApplication extends Application {

    public AcnApplication(String appFont, String loggerTag, int imageFadeInDuration){

        if(!TextUtils.isEmpty(appFont))
            Config.appFont = appFont;

        if(!TextUtils.isEmpty(loggerTag))
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
        AppLevelInitializer.initBaseUtils(this);
        AppLevelInitializer.initLeakCanary(this);
        AppLevelInitializer.initIconify();
        AppLevelInitializer.initCalligraphy(Config.appFont);
        AppLevelInitializer.initImageLoader(this, Config.imageFadeInDuration);
        AppLevelInitializer.initInternetListener(this);

    }

    //REQUIRED FOR MULTIDEX
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        try {
            ImageLoader.getInstance().clearMemoryCache();
            ImageLoader.getInstance().clearDiskCache();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
