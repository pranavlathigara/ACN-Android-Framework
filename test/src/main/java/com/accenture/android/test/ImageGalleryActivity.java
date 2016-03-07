package com.accenture.android.test;

import android.os.Bundle;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.util.ImageClickHandler;
import com.accenture.android.framework.view.AcnImageGallery;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;

public class ImageGalleryActivity extends AcnActivity {

    public ImageGalleryActivity(){
        super(R.layout.activity_imagegallery, R.id.toolbar, R.id.back_button, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> imageURLs = new ArrayList<>();
        imageURLs.add("http://goo.gl/kSv5Nw");
        imageURLs.add("http://goo.gl/w1bC0b");
        imageURLs.add("http://goo.gl/Ao7NsY");
        imageURLs.add("http://goo.gl/hVk5e7");
        imageURLs.add("http://goo.gl/kjyBSG");

        acn_imagegallery.setImagesFromURLs(imageURLs);

        acn_imagegallery.setImageClickHandler(new ImageClickHandler() {
            @Override
            public void onImageClicked(int position, String imageURL) {
                Logger.i("Image clicked: " + position + " - " + imageURL);
            }
        });

    }

    @Subscribe
    @Override
    public void internetCatcher(ConnectivityStatus connectivityStatus){

        Logger.i("Internet catcher: " + connectivityStatus.name());

        Prefs.putString("internetStatus", connectivityStatus.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_imagegallery)
    AcnImageGallery acn_imagegallery;

}
