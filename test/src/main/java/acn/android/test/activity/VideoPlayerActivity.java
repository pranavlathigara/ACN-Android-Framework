package acn.android.test.activity;

import android.os.Bundle;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;
import com.thefinestartist.utils.etc.PreferenceUtil;

import acn.android.framework.context.AcnActivity;
import acn.android.framework.view.AcnVideoView;
import acn.android.test.R;
import butterknife.Bind;

/**
 * Created by ugurcan.yildirim on 10.03.2016.
 */
public class VideoPlayerActivity extends AcnActivity {

    public VideoPlayerActivity(){
        super(R.layout.activity_videoplayer, R.id.toolbar, R.id.back_button, true, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String videoURL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        String thumbnailURL = "https://www.filmfestival.nl/FionaAssets/000001/00000136/scaled/13667.jpg";
        String videoTitle = "Big Buck Bunny";
        boolean showTitle = true;

        acn_videoview.setVideo(videoURL, thumbnailURL, videoTitle, showTitle);

    }

    @Override
    protected void onPause() {
        super.onPause();
        AcnVideoView.releaseAllVideos();
    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        PreferenceUtil.put("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    @Bind(R.id.acn_videoview)
    AcnVideoView acn_videoview;

}
