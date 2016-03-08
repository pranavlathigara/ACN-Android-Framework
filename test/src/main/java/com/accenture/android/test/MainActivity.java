package com.accenture.android.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.view.AcnButton;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.otto.Subscribe;

import butterknife.Bind;

public class MainActivity extends AcnActivity {

    public MainActivity(){
        super(R.layout.activity_main, R.id.toolbar, null, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button_view_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewTestActivity.class);
                startActivity(intent);
            }
        });

        button_zoomable_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZoomableImageActivity.class);
                startActivity(intent);
            }
        });

        button_image_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageGalleryActivity.class);
                startActivity(intent);
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

    @Bind(R.id.button_view_test)
    AcnButton button_view_test;

    @Bind(R.id.button_zoomable_image)
    AcnButton button_zoomable_image;

    @Bind(R.id.button_image_gallery)
    AcnButton button_image_gallery;

}
