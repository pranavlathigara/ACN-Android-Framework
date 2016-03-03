package com.accenture.android.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.accenture.android.framework.context.AcnActivity;
import com.accenture.android.framework.view.AcnButton;
import com.accenture.android.framework.view.AcnImageView;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.otto.Subscribe;

import butterknife.Bind;

public class MainActivity extends AcnActivity {

    public MainActivity(){
        super(R.layout.activity_main, R.id.toolbar, null, R.color.StatusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        acn_imageview.setImageFromURL("http://goo.gl/T59s3M", false);

        acn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(intent);
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

    @Bind(R.id.acn_imageview)
    AcnImageView acn_imageview;

    @Bind(R.id.acn_button)
    AcnButton acn_button;

}
