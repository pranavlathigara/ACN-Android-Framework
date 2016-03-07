package com.accenture.android.framework.util;

import android.app.Application;
import android.content.ContextWrapper;
import android.text.TextUtils;

import com.accenture.android.framework.R;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class AppLevelInitializer {

    public static void initLogger(String loggerTag) {

        if(TextUtils.isEmpty(loggerTag))
            return;

        Logger.init(loggerTag);              // default PRETTYLOGGER or use just init()
        /*      .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
        */

        Logger.i("Logger is initialized!");

    }

    public static void initSharedPrefs(final Application application) {

        new Prefs.Builder()
                .setContext(application)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(application.getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        Logger.i("Prefs is initialized!");

    }

    public static void initIconify() {

        Iconify.with(new FontAwesomeModule());

        Logger.i("Iconify is initialized!");

    }

    public static void initAppFont(String appFont) {

        if(TextUtils.isEmpty(appFont))
            return;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/" + appFont)
                .setFontAttrId(R.attr.fontPath)
                .build());

        Logger.i("Default font is set!");

    }

    public static void initImageLoader(final Application application, int imageFadeInDuration) {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .displayer(new FadeInBitmapDisplayer(imageFadeInDuration))
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .cacheInMemory(false).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(application)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPoolSize(5)
                /*.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .discCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .discCache(new UnlimitedDiscCache(cacheDir)) // default
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder()) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()*/
                .build();
        ImageLoader.getInstance().init(config);

        Logger.i("Image loader is initialized!");

    }

    public static void initInternetListener(final Application application) {

        new ReactiveNetwork().observeConnectivity(application)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override
                    public void call(ConnectivityStatus connectivityStatus) {
                        //POST CONNECTIVITY STATUS
                        BusProvider.getInstance().post(connectivityStatus);
                    }
                });

        Logger.i("Internet listener is initialized!");

    }

}
