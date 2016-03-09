package com.accenture.android.test;

import android.os.Bundle;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.view.AcnImagePager;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;

public class ZoomableImagePagerActivity extends AcnActivity {

    public ZoomableImagePagerActivity(){
        super(R.layout.activity_imagepager_zoomable, R.id.toolbar, R.id.back_button, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> imageURLs = new ArrayList<>();
        imageURLs.add("http://goo.gl/I9e5GH");
        imageURLs.add("https://goo.gl/m2CkdC");
        imageURLs.add("http://goo.gl/a1ab9F");
        imageURLs.add("http://goo.gl/OtTrsj");
        imageURLs.add("http://goo.gl/1EldJ2");

        acn_imagepager_zoomable.setImagesFromURLList(imageURLs, true);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        Prefs.putString("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_imagepager_zoomable)
    AcnImagePager acn_imagepager_zoomable;

}
