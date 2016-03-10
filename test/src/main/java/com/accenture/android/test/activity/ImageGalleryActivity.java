package com.accenture.android.test.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.LinearLayout;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.util.ImageClickHandler;
import com.accenture.android.framework.view.AcnImageGallery;
import com.accenture.android.test.R;
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
        imageURLs.add("http://goo.gl/I9e5GH");
        imageURLs.add("https://goo.gl/m2CkdC");
        imageURLs.add("http://goo.gl/a1ab9F");
        imageURLs.add("http://goo.gl/onGsc9");
        imageURLs.add("http://goo.gl/1EldJ2");

        acn_imagegallery_pairs.setImagesFromURLList(imageURLs);

        acn_imagegallery_pairs.setImageClickHandler(new ImageClickHandler() {
            @Override
            public void onImageClicked(int position, String imageURL) {

                Logger.i("Image clicked: " + position + " - " + imageURL);
                Snackbar.make(container, "Image clicked: " + position + " - " + imageURL, Snackbar.LENGTH_SHORT).show();

            }
        });

        acn_imagegallery_triplets.setImagesFromURLList(imageURLs);

        acn_imagegallery_triplets.setImageClickHandler(new ImageClickHandler() {
            @Override
            public void onImageClicked(int position, String imageURL) {

                Logger.i("Image clicked: " + position + " - " + imageURL);
                Snackbar.make(container, "Image clicked: " + position + " - " + imageURL, Snackbar.LENGTH_SHORT).show();

            }
        });

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        Prefs.putString("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.container)
    LinearLayout container;

    @Bind(R.id.acn_imagegallery_pairs)
    AcnImageGallery acn_imagegallery_pairs;

    @Bind(R.id.acn_imagegallery_triplets)
    AcnImageGallery acn_imagegallery_triplets;

}
