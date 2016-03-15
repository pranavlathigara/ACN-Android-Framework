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

public class ImageActivity extends AcnActivity {

    public ImageActivity(){
        super(R.layout.activity_image, R.id.toolbar, R.id.back_button, true, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean zoomable = false;

        acn_imageview_url.setImageFromURL("http://goo.gl/T59s3M", zoomable);

        acn_imageview_drawable.setImageFromDrawable(R.drawable.image, zoomable);

        acn_imageview_asset.setImageFromAssets("image.jpg", zoomable);

        acn_imageview_url_custom_loading.setImageFromURL("http://goo.gl/T59s3M", zoomable);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        PreferenceUtil.put("internetStatus", status.name());
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
