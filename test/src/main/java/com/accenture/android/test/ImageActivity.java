package com.accenture.android.test;

import android.os.Bundle;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.view.AcnImageView;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.otto.Subscribe;

import butterknife.Bind;

public class ImageActivity extends AcnActivity {

    public ImageActivity(){
        super(R.layout.activity_image, R.id.toolbar, R.id.back_button, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        acn_imageview_url.setImageFromURL("http://goo.gl/T59s3M", false);

        acn_imageview_drawable.setImageFromDrawable(R.drawable.image, false);

        acn_imageview_asset.setImageFromAssets("image.jpg", false);

        acn_imageview_url_custom_loading.setImageFromURL("http://goo.gl/T59s3M", false);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        Prefs.putString("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_imageview_url)
    AcnImageView acn_imageview_url;

    @Bind(R.id.acn_imageview_drawable)
    AcnImageView acn_imageview_drawable;

    @Bind(R.id.acn_imageview_asset)
    AcnImageView acn_imageview_asset;

    @Bind(R.id.acn_imageview_url_custom_loading)
    AcnImageView acn_imageview_url_custom_loading;

}
