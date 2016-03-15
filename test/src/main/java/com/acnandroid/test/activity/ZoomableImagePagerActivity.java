package com.acnandroid.test.activity;

import android.os.Bundle;

import com.acnandroid.framework.context.AcnActivity;
import com.acnandroid.framework.view.AcnImagePager;
import com.acnandroid.test.R;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;
import com.thefinestartist.utils.etc.PreferenceUtil;

import java.util.ArrayList;

import butterknife.Bind;

public class ZoomableImagePagerActivity extends AcnActivity {

    public ZoomableImagePagerActivity(){
        super(R.layout.activity_imagepager_zoomable, R.id.toolbar, R.id.back_button, true, R.color.statusBar);
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

        boolean zoomable = true;

        acn_imagepager_zoomable.setImagesFromURLList(imageURLs, zoomable);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        PreferenceUtil.put("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_imagepager_zoomable)
    AcnImagePager acn_imagepager_zoomable;

}
