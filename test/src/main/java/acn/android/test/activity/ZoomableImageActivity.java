package acn.android.test.activity;

import android.os.Bundle;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;
import com.thefinestartist.utils.etc.PreferenceUtil;

import acn.android.framework.context.AcnActivity;
import acn.android.framework.view.AcnImageView;
import acn.android.test.R;
import butterknife.Bind;

public class ZoomableImageActivity extends AcnActivity {

    public ZoomableImageActivity(){
        super(R.layout.activity_image_zoomable, R.id.toolbar, R.id.back_button, true, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean zoomable = true;

        acn_imageview_zoomable.setImageFromURL("http://goo.gl/Ny2WcW", zoomable);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        PreferenceUtil.put("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_imageview_zoomable)
    AcnImageView acn_imageview_zoomable;

}
