package com.accenture.android.test;

import android.os.Bundle;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.view.AcnImageView;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.otto.Subscribe;

import butterknife.Bind;

public class SecondaryActivity extends AcnActivity {

    public SecondaryActivity(){
        super(R.layout.activity_secondary, R.id.toolbar, R.id.back_button, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        acn_imageview_zoomable.setImageFromURL("http://goo.gl/56MHKp", true);

    }

    @Subscribe
    @Override
    public void internetCatcher(ConnectivityStatus connectivityStatus){

        Logger.i("Internet catcher: " + connectivityStatus.name());

        Prefs.putString("internetStatus", connectivityStatus.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_imageview_zoomable)
    AcnImageView acn_imageview_zoomable;

}